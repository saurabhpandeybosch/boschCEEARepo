/**
 *
 */
package com.ceea.core.utils;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ceea.facades.product.data.AnnotationData;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * @author AEN8KOR
 *
 */
public final class AnnotationJSonUtil
{
	public static void convertJsonToObject(final ProductData target) throws IOException
	{
		final ObjectMapper mapper = new ObjectMapper();
		AnnotationData[] readValue = null;
		if (StringUtils.isNotEmpty(target.getAnnotation()))
		{
			readValue = mapper.readValue(target.getAnnotation(), AnnotationData[].class);
			final List<AnnotationData> list = Arrays.asList(readValue);
			target.setAnnotationList(list);
		}

	}
}
