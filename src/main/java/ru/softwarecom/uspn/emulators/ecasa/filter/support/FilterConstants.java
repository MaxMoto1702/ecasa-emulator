package ru.softwarecom.uspn.emulators.ecasa.filter.support;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_FORWARD_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * @author Max Serebryanskiy
 */
public final class FilterConstants {

	// ORDER constants -----------------------------------

	public static final int START_USER_ORDER = SEND_FORWARD_FILTER_ORDER;
	public static final int END_USER_ORDER = SEND_RESPONSE_FILTER_ORDER;

	/**
	 * Filter Order for
	 * {@link ru.softwarecom.uspn.emulators.ecasa.filter.pre.EcasaHeadersFilter#filterOrder()}.
	 */
	public static final int ECASA_HEADERS_FILTER_ORDER = START_USER_ORDER + 10;

	/**
	 * Filter Order for {@link ru.softwarecom.uspn.emulators.ecasa.filter.post.StaticResponseFilter#filterOrder()}.
	 */
	public static final int STATIC_RESPONSE_FILTER_ORDER = START_USER_ORDER + 10;

	/**
	 * Filter Order for {@link ru.softwarecom.uspn.emulators.ecasa.filter.post.TraceUnmodifiedFilter#filterOrder()}.
	 */
	public static final int TRACE_UNMODIFIED_FILTER_ORDER = START_USER_ORDER + 20;

	/**
	 * Filter Order for {@link ru.softwarecom.uspn.emulators.ecasa.filter.post.EcasaHtmlHeaderFilter#filterOrder()}.
	 */
	public static final int ECASA_HTML_HEADER_FILTER_ORDER = START_USER_ORDER + 31;

	/**
	 * Filter Order for {@link ru.softwarecom.uspn.emulators.ecasa.filter.post.EcasaHtmlUrlFilter#filterOrder()}.
	 */
	public static final int ECASA_HTML_URL_FILTER_ORDER = START_USER_ORDER + 32;

	/**
	 * Filter Order for {@link ru.softwarecom.uspn.emulators.ecasa.filter.post.TraceModifiedFilter#filterOrder()}.
	 */
	public static final int TRACE_MODIFIED_FILTER_ORDER = END_USER_ORDER - 10;

	private FilterConstants() {
		throw new AssertionError("Must not instantiate constant utility class");
	}

}
