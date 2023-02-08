package com.ceea.facades.populators;

import de.hybris.platform.commercefacades.product.converters.populator.ProductPopulator;
import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ceea.core.utils.AnnotationJSonUtil;


/**
 * @author ZKS7KOR
 *
 */
public class CeeaProductPopulator extends ProductPopulator
{
	private static final Logger LOG = LoggerFactory.getLogger(CeeaProductPopulator.class);

	@Resource
	private Converter<MediaModel, ImageData> imageConverter;

	/**
	 * @return the imageConverter
	 */
	public Converter<MediaModel, ImageData> getImageConverter()
	{
		return imageConverter;
	}

	/**
	 * @param imageConverter
	 *           the imageConverter to set
	 */
	public void setImageConverter(final Converter<MediaModel, ImageData> imageConverter)
	{
		this.imageConverter = imageConverter;
	}

	@Override
	public void populate(final ProductModel source, final ProductData target)
	{

		target.setAnnotation(source.getAnnotation());
		final MediaModel media = source.getThreeDimensionalImage();
		if (media != null)
		{
			final ImageData imageData = getImageConverter().convert(media);
			target.setCeeaThreeDimensionalImage(imageData);
		}
		final MediaModel primaryImg=source.getPicture();
		if(null!=primaryImg) {
			target.setPrimaryImgURL(primaryImg.getURL());
		}

		target.setProductVideoUrl(source.getProductVideoUrl());
		final MediaModel productPDF = source.getProductPDF();
		if (productPDF != null)
		{
			final ImageData convert = getImageConverter().convert(productPDF);
			target.setProductPDF(convert);
		}
		// super.populate(source, target);
		try {
			AnnotationJSonUtil.convertJsonToObject(target);
		} catch (final IOException e) {
			LOG.info("Exception Occured :: "+e.getMessage());
		}
	}

}

