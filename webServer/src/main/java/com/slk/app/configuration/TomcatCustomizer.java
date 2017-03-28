package com.slk.app.configuration;

import org.springframework.boot.context.embedded.Compression;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * Created by marco on 20.02.17.
 */
@Component
public class TomcatCustomizer implements EmbeddedServletContainerCustomizer {

    private static String mimeTypes[] = {"text/html", "text/plain", "text/xml", "text/css", "text/javascript", "application/javascript"};

    @Override
    public void customize(ConfigurableEmbeddedServletContainer servletContainer) {

        Compression compression = new Compression();
        compression.setEnabled(true);

        compression.setMimeTypes(mimeTypes);
        servletContainer.setCompression(compression);
        /*
		    TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		    factory.setPort(9000);
		    factory.setSessionTimeout(10, TimeUnit.MINUTES);
		    factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
		    return factory;
         * */
        
    }
}
