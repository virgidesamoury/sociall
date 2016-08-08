/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package be.isl.desamouryv.sociall.ui.detail;

import be.isl.desamouryv.sociall.domain.Product;
import java.util.logging.Logger;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Virginie
 */
@Named
@ViewScoped
public class ProductDetailController extends DetailController<Product> {

    private static final Logger logger = Logger.getLogger(ProductDetailController.class.getName());
    
    public ProductDetailController() {
    }
}
