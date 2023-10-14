package com.proyect.agroferreteria.services.implementation;

import com.proyect.agroferreteria.models.entity.Product;
import com.proyect.agroferreteria.models.entity.TypeProduct;
import com.proyect.agroferreteria.repository.ITypeProductRepository;
import com.proyect.agroferreteria.services.contracts.ITypeProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeProductImpl implements ITypeProductService {
    @Autowired
    private ITypeProductRepository typeProductRepository;
    @Override
    @Transactional(readOnly = true)
    public List<TypeProduct> findAll() {
        return (List<TypeProduct>) typeProductRepository.findAll();
    }

    @Override
    @Transactional
    public TypeProduct save(TypeProduct typeProduct, List<Product> products) throws Exception {
        TypeProduct typeProductLocal = typeProductRepository.findByName(typeProduct.getName());
        if(typeProductLocal != null){
            throw new Exception("El tipo de producto ya existe");

        }else {
            for (Product product:products){
                typeProductRepository.save(product.getTypeProduct());
            }
            typeProduct.getProducts().addAll(products);
            typeProductLocal = typeProductRepository.save(typeProduct);
        }
        return typeProductLocal;
    }



    @Override
    @Transactional(readOnly = true)
    public TypeProduct findById(Long id) {
        return typeProductRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if(id > 0){
            typeProductRepository.deleteById(id);
        }
    }

    @Override
    public TypeProduct getByName(String name) {
        return typeProductRepository.findByName(name);
    }
}
