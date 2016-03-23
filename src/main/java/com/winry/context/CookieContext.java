package com.winry.context;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.winry.dto.LoginResult;
import com.winry.service.LoginService;
import com.winry.util.Tuple_2;

@Component
public class CookieContext {

	private static final String[] NAMES = { "DedeUserID", "DedeUserID__ckMd5", "SESSDATA", "Expires" };

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
			items = Splitter.on("&").omitEmptyStrings().trimResults().withKeyValueSeparator("=")
					.split(crossDomain);
			int expires = Integer.valueOf(items.get(NAMES[3]));
			expireDate = LocalDateTime.now().plus(expires, ChronoUnit.MINUTES);
		}
	}

	public Tuple_2 getHeader() {
		if (expireDate.compareTo(LocalDateTime.now()) < 0) {
			refresh();
		}
		String cookie = Joiner.on(";").withKeyValueSeparator("=").join(items);
		return new Tuple_2("Cookie", cookie);
	}

}
