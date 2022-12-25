/**
 * 
 */
package com.ledzer.master.utility;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;

import org.springframework.util.StringUtils;

/**
 * @author Sagar
 *
 */
public class UtilityMethods {
	private static Properties applicationProperties = null;

	private static Logger logger = LogManager.getLogger(UtilityMethods.class);

	/**
	 * This method will get offset for a page.
	 * 
	 * @param offset - offset value
	 * @param limit  - limit values
	 * @return offset
	 */
	private static int getOffset(Integer offset, int limit) {
		if (offset == null || offset == 0) {
			offset = Constants.PAGE_OFFSET_DEFAULT;
		} else {
			if (offset > Constants.PAGE_OFFSET_MAX_LIMIT) {
				offset = Constants.PAGE_OFFSET_MAX_LIMIT;
			}

			if (offset <= Constants.PAGE_OFFSET_DEFAULT) {
				offset = Constants.PAGE_OFFSET_DEFAULT;
			}

			if (offset != null && offset < Constants.PAGE_OFFSET_MAX_LIMIT) {
				return offset.intValue();
			} else {
				offset = (offset * limit) - limit;
			}
		}

		return offset.intValue();
	}

	/**
	 * This method will get offset for a page.
	 * 
	 * @param  - offset value
	 * @param limit  - limit values
	 * @return offset
	 */
	private static int getOffset(String offsetStr, int limit) {
		if (StringUtils.isEmpty(offsetStr)) {
			return Constants.PAGE_OFFSET_DEFAULT;
		} else {
			int offset = Integer.parseInt(offsetStr);
			if (offset > Constants.PAGE_OFFSET_MAX_LIMIT) {
				offset = Constants.PAGE_OFFSET_MAX_LIMIT;
			}

			if (offset <= Constants.PAGE_OFFSET_DEFAULT) {
				offset = Constants.PAGE_OFFSET_DEFAULT;
			}

			if (offset < Constants.PAGE_OFFSET_MAX_LIMIT) {
				return offset;
			} else {
				offset = (offset * limit) - limit;
			}

			return offset;
		}
	}

	/**
	 * This method will get limit for a page.
	 * 
	 * @param limit - limit value
	 * @return limit
	 */
	private static int getLimit(Integer limit) {
		if (limit == null || limit == 0) {
			limit = Constants.PAGE_LIMIT_DEFAULT;
		} else {
			if (limit < 0) {
				limit = Constants.PAGE_LIMIT_DEFAULT;
			}

			if (limit > Constants.PAGE_LIMIT_MAX) {
				limit = Constants.PAGE_LIMIT_MAX;
			}
		}

		return limit.intValue();
	}

	/**
	 * This method will get limit for a page.
	 * 
	 * @param  - limit value
	 * @return limit
	 */
	private static int getLimit(String limitStr) {
		if (StringUtils.isEmpty(limitStr)) {
			return Constants.PAGE_LIMIT_DEFAULT;
		} else {
			int limit = Integer.parseInt(limitStr);

			if (limit < 0) {
				limit = Constants.PAGE_LIMIT_DEFAULT;
			}

			if (limit > Constants.PAGE_LIMIT_MAX) {
				limit = Constants.PAGE_LIMIT_MAX;
			}

			return limit;
		}
	}


	/**
	 * This method will generate the pageable instance for a given set of
	 * parameters.
	 * 
	 * @param         - janrainId's of persons.
	 * @param     - person email.
	 * @param  - person types.
	 * @param limit      - limit of records.
	 * @param offset     - page number.
	 * @return Pageable - pageable instance for a given set of parameters.
	 */
	public static Pageable generatePage(Integer limit, Integer offset) {

		System.out.println("limit before: " + limit);
		limit = getLimit(limit);
		System.out.println("limit after: " + limit);

		System.out.println("offset before: " + offset);
		offset = getOffset(offset, limit);
		System.out.println("offset after: " + offset);

		Pageable pageable = PageRequest.of(offset, limit);

		return pageable;
	}

	/**
	 * @param
	 * @param
	 * @return
	 */
	public static Pageable generatePage(String limitStr, String offsetStr) {
		System.out.println("limit before: " + limitStr);
		int limit = getLimit(limitStr);
		System.out.println("limit after: " + limit);

		System.out.println("offset before: " + offsetStr);
		int offset = getOffset(offsetStr, limit);
		System.out.println("offset after: " + offsetStr);

		Pageable pageable = PageRequest.of(offset, limit);

		return pageable;
	}
}
