package org.nercita.core.web.springmvc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.core.convert.ConversionService;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 数据类型绑定
 * @author wangzz
 * @since 2010-01-05
 */
public class MyBindingInitializer implements WebBindingInitializer  {
	
	private boolean directFieldAccess = false;

	private MessageCodesResolver messageCodesResolver;

	private BindingErrorProcessor bindingErrorProcessor;

	private Validator validator;

	private ConversionService conversionService;

	private PropertyEditorRegistrar[] propertyEditorRegistrars;


	public final void setDirectFieldAccess(boolean directFieldAccess) {
		this.directFieldAccess = directFieldAccess;
	}

	public final void setMessageCodesResolver(MessageCodesResolver messageCodesResolver) {
		this.messageCodesResolver = messageCodesResolver;
	}

	public final MessageCodesResolver getMessageCodesResolver() {
		return this.messageCodesResolver;
	}

	public final void setBindingErrorProcessor(BindingErrorProcessor bindingErrorProcessor) {
		this.bindingErrorProcessor = bindingErrorProcessor;
	}

	public final BindingErrorProcessor getBindingErrorProcessor() {
		return this.bindingErrorProcessor;
	}

	public final void setValidator(Validator validator) {
		this.validator = validator;
	}

	public final Validator getValidator() {
		return this.validator;
	}

	public final void setConversionService(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	public final ConversionService getConversionService() {
		return this.conversionService;
	}

	public final void setPropertyEditorRegistrar(PropertyEditorRegistrar propertyEditorRegistrar) {
		this.propertyEditorRegistrars = new PropertyEditorRegistrar[] {propertyEditorRegistrar};
	}

	public final void setPropertyEditorRegistrars(PropertyEditorRegistrar[] propertyEditorRegistrars) {
		this.propertyEditorRegistrars = propertyEditorRegistrars;
	}

	public final PropertyEditorRegistrar[] getPropertyEditorRegistrars() {
		return this.propertyEditorRegistrars;
	}

	public void initBinder(WebDataBinder binder, WebRequest request) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  //可以設定任意的日期格式   
        dateFormat.setLenient(false);   
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));   
        binder.registerCustomEditor(Short.class, new CustomNumberEditor(Short.class, true));
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
        binder.registerCustomEditor(Long.class, new CustomNumberEditor(Long.class, true));
        binder.registerCustomEditor(Float.class, new CustomNumberEditor(Float.class, true));
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true));
        binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(BigDecimal.class, true));
        binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(BigInteger.class, true));
        if (this.directFieldAccess) {
			binder.initDirectFieldAccess();
		}
		if (this.messageCodesResolver != null) {
			binder.setMessageCodesResolver(this.messageCodesResolver);
		}
		if (this.bindingErrorProcessor != null) {
			binder.setBindingErrorProcessor(this.bindingErrorProcessor);
		}
		if (this.validator != null && binder.getTarget() != null &&
				this.validator.supports(binder.getTarget().getClass())) {
			binder.setValidator(this.validator);
		}
		if (this.conversionService != null) {
			binder.setConversionService(this.conversionService);
		}
		if (this.propertyEditorRegistrars != null) {
			for (PropertyEditorRegistrar propertyEditorRegistrar : this.propertyEditorRegistrars) {
				propertyEditorRegistrar.registerCustomEditors(binder);
			}
		}
	}
	
}
