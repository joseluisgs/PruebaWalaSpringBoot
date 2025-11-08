package com.joseluisgs.walaspringboot;

import com.joseluisgs.walaspringboot.modelos.Producto;
import com.joseluisgs.walaspringboot.modelos.Usuario;
import com.joseluisgs.walaspringboot.repositorios.ProductoRepository;
import com.joseluisgs.walaspringboot.repositorios.UsuarioRepository;
import com.joseluisgs.walaspringboot.servicios.ProductoServicio;
import com.joseluisgs.walaspringboot.servicios.UsuarioServicio;
import com.joseluisgs.walaspringboot.upload.StorageProperties;
import com.joseluisgs.walaspringboot.upload.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;


import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * EnableAutoConfiguration: le dice a Spring Boot que comience a agregar beans según la configuración de classpath,
 * otros beans y varias configuraciones de propiedades.
 * Por ejemplo, si spring-webmvc está en el classpath, esta anotación marca la aplicación como una aplicación web y
 * activa comportamientos clave, como configurar un DispatcherServlet o saber donde buscar las propiedades de Almacenamiento.
 */
@EnableConfigurationProperties(StorageProperties.class)

@SpringBootApplication
public class WalaSpringBootApplication {



    public static void main(String[] args) {
        //Locale.setDefault(new Locale("es", "ES"));
        SpringApplication.run(WalaSpringBootApplication.class, args);
    }

    // Con este Bean lo que hacemos es iniciar con algunos datos la aplicacion
    // Para ello se usa CoomandLineRunner. Es para cargar datos en la BB.DD
    // V1.0 Lo hacemos usando repostorios idirectamente
    /*
    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        return args -> {

            Usuario usuario = new Usuario("Prueba", "Probando Mucho", null, "prueba", "prueba");
            usuario = usuarioRepository.save(usuario);

            productoRepository.saveAll(Arrays.asList(
                    new Producto("Bicicleta de montaÃ±a", 100.0f,
                            "https://www.decathlon.es/media/835/8350582/big_23c25284-2810-415d-8bcc-e6bebdb536fc.jpg", usuario),
                    new Producto("Golf GTI Serie 2", 2500.0f,
                            "https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg",
                            usuario),
                    new Producto("Raqueta de tenis", 10.5f, "https://imgredirect.milanuncios.com/fg/2311/04/tenis/Raqueta-tenis-de-segunda-mano-en-Madrid-231104755_1.jpg?VersionId=T9dPhTf.3ZWiAFjnB7CvGKsvbdfPLHht", usuario),
                    new Producto("Xbox One X", 425.0f, "https://images.vibbo.com/635x476/860/86038583196.jpg", usuario),
                    new Producto("TrÃ­pode flexible", 10.0f, "https://images.vibbo.com/635x476/860/86074256163.jpg", usuario),
                    new Producto("Iphone 7 128 GB", 350.0f, "https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982", usuario)
            ));



        };
    }*/

    // V2.0 Lo hacemos usando los servicios

    @Bean
    public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
        return args -> {

            Usuario usuario = new Usuario("Prueba", "Probando Mucho", null, "prueba@prueba.com", "prueba");
            usuario = usuarioServicio.registrar(usuario);

            Usuario usuario2 = new Usuario("Otro", "Usuario", null, "otro@otro.com", "otro");
            usuario2 = usuarioServicio.registrar(usuario2);


            List<Producto> listado = Arrays.asList(new Producto("Bicicleta de montaña", 100.0f,
                            "https://sgfm.elcorteingles.es/SGFM/dctm/MEDIA03/201809/20/00108451997269____4__640x640.jpg", usuario),
                    new Producto("Golf GTI Serie 2", 2500.0f,
                            "https://www.minicar.es/large/Volkswagen-Golf-GTi-G60-Serie-II-%281990%29-Norev-1%3A18-i22889.jpg",
                            usuario),
                    new Producto("Raqueta de tenis", 10.5f, "https://imgredirect.milanuncios.com/fg/2311/04/tenis/Raqueta-tenis-de-segunda-mano-en-Madrid-231104755_1.jpg?VersionId=T9dPhTf.3ZWiAFjnB7CvGKsvbdfPLHht", usuario),
                    new Producto("Xbox One X", 425.0f, "https://images.vibbo.com/635x476/860/86038583196.jpg", usuario2),
                    new Producto("Trípode flexible", 10.0f, "https://images.vibbo.com/635x476/860/86074256163.jpg", usuario2),
                    new Producto("Iphone 7 128 GB", 350.0f, "https://store.storeimages.cdn-apple.com/4667/as-images.apple.com/is/image/AppleInc/aos/published/images/i/ph/iphone7/rosegold/iphone7-rosegold-select-2016?wid=470&hei=556&fmt=jpeg&qlt=95&op_usm=0.5,0.5&.v=1472430205982", usuario2));

            listado.forEach(productoServicio::insertar);


        };
    }

    /**
     * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el almacenamiento
     * secundario del proyecto
     *
     * @param storageService Almacenamiento secundario del proyecto
     * @return
     */
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            // Borramos todas
            storageService.deleteAll();
            // iniciamos el directorio
            storageService.init();
        };
    }



}
