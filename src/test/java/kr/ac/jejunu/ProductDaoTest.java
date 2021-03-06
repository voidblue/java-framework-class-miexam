package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    ProductDao productDao;
//    ProductDao hallaProductDao;
    DaoFactory daoFactory;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(DaoFactory.class);
        productDao = applicationContext.getBean("getDao", ProductDao.class);
//        hallaProductDao = new ProductDao(new HallaConnectionMaker());

    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = productDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        Product product = new Product();

        product.setTitle("사과");
        product.setPrice(10000);

        Long insertedId = productDao.insert(product);
        Product insertedProduct = productDao.get(insertedId);
        assertThat(insertedProduct.getId(), is(insertedId));
        assertThat(insertedProduct.getTitle(), is(product.getTitle()));
        assertThat(insertedProduct.getPrice(), is(product.getPrice()));

    }
    @Test
    public void update() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setId(2L);
        product.setTitle("배");
        product.setPrice(20000);

        productDao.update(product);
        Product updatedProduct = productDao.get(2L);

        assertThat(updatedProduct.getId(), is(2L));
        assertThat(updatedProduct.getTitle(), is(product.getTitle()));
        assertThat(updatedProduct.getPrice(), is(product.getPrice()));

    }

    @Test
    public void delete() throws SQLException, ClassNotFoundException {
        Product product = new Product();
        product.setTitle("xxx");
        product.setPrice(0);

        Long insertedId = productDao.insert(product);
        productDao.delete(insertedId);

        Product deletedProduct = productDao.get(insertedId);

        assertThat(deletedProduct, is(nullValue()));
    }


//
//    @Test
//    public void hallaGet() throws SQLException, ClassNotFoundException {
//        Long id = 1L;
//        String title = "제주감귤";
//        Integer price = 15000;
//
//        Product product = hallaProductDao.get(id);
//        assertEquals(id, product.getId());
//        assertEquals(title, product.getTitle());
//        assertEquals(price, product.getPrice());
//    }
//
//    @Test
//    public void hallaInsert() throws SQLException, ClassNotFoundException {
//        Product product = new Product();
//
//        product.setTitle("사과");
//        product.setPrice(10000);
//
//        Long insertedId = hallaProductDao.insert(product);
//        Product insertedProduct = hallaProductDao.get(insertedId);
//        assertThat(insertedProduct.getId(), is(insertedId));
//        assertThat(insertedProduct.getTitle(), is(product.getTitle()));
//        assertThat(insertedProduct.getPrice(), is(product.getPrice()));
//
//    }
}
