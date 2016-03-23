package com.winry.context;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Splitter;
import com.winry.dto.LoginResult;
import com.winry.service.LoginService;

@Component
public class Cookie {

	private static final String[] NAMES = { "DedeUserID", "DedeUserID__ckMd5", "SESSDATA", "Expires" };

	private Date expireDate;

	private final List<Item> items = new ArrayList<>();

	@Autowired
	private LoginService loginService;

	@PostConstruct
	public void init() {
		LoginResult result = loginService.login();
		if (result.success()) {
			String crossDomain = result.getData().getCrossDomain();
			crossDomain = StringUtils.substringAfter(crossDomain, "?");
			Map<String, String> splitted = Splitter.on("&").omitEmptyStrings().trimResults().withKeyValueSeparator("=")
					.split(crossDomain);
			splitted.forEach((key, value) -> {
			});
		}
	}

	@Override
	public String toString() {
		return "Cookie [expireDate=" + expireDate + ", items=" + items + "]";
	}

	private static class Item {

		String name;

		String value;

		String domain;

		Date expireDate;

		String path;

		public Item(String name, String value, String domain, Date expireDate, String path) {
			super();
			this.name = name;
			this.value = value;
			this.domain = domain;
			this.expireDate = expireDate;
			this.path = path;
		}

	}

}
