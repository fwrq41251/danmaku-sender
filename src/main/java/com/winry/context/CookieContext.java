package com.winry.context;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.winry.dto.LoginResult;
import com.winry.service.LoginService;
import com.winry.util.Tuple2;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

/**
 * Context that hold cookie info for api that need auth to use.
 *
 */
@Component
public class CookieContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(CookieContext.class);

    private static final String[] NAMES = {"DedeUserID", "DedeUserID__ckMd5", "SESSDATA", "Expires"};

    private LocalDateTime expireDate;

    private Map<String, String> items;

    @Autowired
    private LoginService loginService;

    @PostConstruct
    public void refresh() {
        LoginResult result = loginService.login();
        if (result.success()) {
            String crossDomain = result.getData().getCrossDomain();
            crossDomain = StringUtils.substringAfter(crossDomain, "?");
            /**
             * items and expireDate can be accessed by multiThread.
             */
            synchronized (this) {
                items = Splitter.on("&").omitEmptyStrings().trimResults().withKeyValueSeparator("=")
                        .split(crossDomain);
                int expires = Integer.valueOf(items.get(NAMES[3]));
                expireDate = LocalDateTime.now().plus(expires, ChronoUnit.MINUTES);
            }
            LOGGER.debug("login success");
        }
    }

    /**
     * @return add to http request header.
     */
    public Tuple2 getHeader() {
        if (expireDate.compareTo(LocalDateTime.now()) < 0) {
            refresh();
        }
        String cookie = Joiner.on(";").withKeyValueSeparator("=").join(items);
        return new Tuple2("Cookie", cookie);
    }

}
