import static org.junit.Assert.*;

import java.io.Reader;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;
import com.ibatis.jpetstore.persistence.DaoConfig;
import com.ibatis.jpetstore.service.CatalogService;

public class CatalogServiceTest {

	static CatalogService catalogService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		catalogService = CatalogService.getInstance();
	}

	@Test
	public void testGetCategoryList() {
		assertNotNull(catalogService.getCategoryList());
	}

	@Test
	public void testGetCategory() {
		assertNotNull(catalogService.getCategory("BIRDS"));
	}

	@Test
	public void testGetCategoryWithProducts() {
		assertNotNull(catalogService.getCategoryWithProducts("BIRDS"));
	}

	@Test
	public void testGetProduct() {
		assertNotNull(catalogService.getProduct("AV-CB-01"));
	}

	@Test
	public void testGetProductListByCategory() {
		assertNotNull(catalogService.getProductListByCategory("BIRDS"));
	}

	@Test
	public void testSearchProductList() {
		assertNotNull(catalogService.searchProductList("Tiger"));
	}

	@Test
	public void testGetItemListByProduct() {
		assertNotNull(catalogService.getItemListByProduct("AV-CB-01"));
	}

	@Test
	public void testGetItem() {
		assertNotNull(catalogService.getItem("EST-1"));
	}

	@Test
	public void testIsItemInStock() {
		assertNotNull(catalogService.isItemInStock("EST-1"));
	}

}
