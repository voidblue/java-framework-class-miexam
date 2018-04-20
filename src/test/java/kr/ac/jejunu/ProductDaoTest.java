package kr.ac.jejunu;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductDaoTest {
    ProductDao jejuProductDao;
    ProductDao hallaProductDao;

    @Before
    public void setup() {
        jejuProductDao = new JejuProductDao();
        hallaProductDao = new HallaProductDao();
    }

    @Test
    public void get() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = jejuProductDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void insert() throws SQLException, ClassNotFoundException {
        Product product = new Product();

        product.setTitle("사과");
        product.setPrice(10000);

        Long insertedId = jejuProductDao.insert(product);
        Product insertedProduct = jejuProductDao.get(insertedId);
        assertThat(insertedProduct.getId(), is(insertedId));
        assertThat(insertedProduct.getTitle(), is(product.getTitle()));
        assertThat(insertedProduct.getPrice(), is(product.getPrice()));

    }

    @Test
    public void hallaGet() throws SQLException, ClassNotFoundException {
        Long id = 1L;
        String title = "제주감귤";
        Integer price = 15000;

        Product product = hallaProductDao.get(id);
        assertEquals(id, product.getId());
        assertEquals(title, product.getTitle());
        assertEquals(price, product.getPrice());
    }

    @Test
    public void hallaInsert() throws SQLException, ClassNotFoundException {
        Product product = new Product();

        product.setTitle("사과");
        product.setPrice(10000);

        Long insertedId = hallaProductDao.insert(product);
        Product insertedProduct = hallaProductDao.get(insertedId);
        assertThat(insertedProduct.getId(), is(insertedId));
        assertThat(insertedProduct.getTitle(), is(product.getTitle()));
        assertThat(insertedProduct.getPrice(), is(product.getPrice()));

    }
}
