import static org.junit.Assert.*;

import java.io.Reader;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ibatis.common.resources.Resources;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.DaoManagerBuilder;
import com.ibatis.jpetstore.persistence.DaoConfig;
import com.ibatis.jpetstore.service.OrderService;

public class OrderServiceTest {
	
	static OrderService orderService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		orderService = new OrderService();
	}

	/*@Test
	public void testInsertOrder() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGetOrder() {
		assertNotNull(orderService.getOrder(1000));
	}

	@Test
	public void testGetOrdersByUsername() {
		assertNotNull(orderService.getOrdersByUsername("j2ee"));
	}

	@Test
	public void testGetNextId() {
		assertNotNull(orderService.getNextId("ordernum"));
	}

}
