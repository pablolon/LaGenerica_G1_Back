package com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.misiontic2022.grupo51.tiendasgenericas.lagenericag51.spring.data.mongodb.model.Producto;


public interface ProductoRepository extends MongoRepository<Producto, String>{
	
	List<Producto> findByCodigoproducto(Long codigoproducto);
	
	List<Producto> findByNombreproducto(String nombreproducto);

}
