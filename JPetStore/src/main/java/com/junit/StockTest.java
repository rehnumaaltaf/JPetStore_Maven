/**
 * 
 */
package com.junit;

import static org.junit.Assert.*;

import org.junit.Test;

import com.ibatis.jpetstore.persistence.sqlmapdao.ItemSqlMapDao;

/**
 * @author rahul_agrawal09
 *
 */
public class StockTest {

	/**
	 * Test method for {@link com.ibatis.jpetstore.persistence.sqlmapdao.ItemSqlMapDao#isItemInStockTG(java.lang.String)}.
	 */
	@Test
	public void testIsItemInStockTG() {
		ItemSqlMapDao dao = new ItemSqlMapDao(null);
		
		assertTrue("Passed", dao.isItemInStockTG("EST-1"));
	}
	
	@Test
	public void testIsItemInStockTG1() {
		ItemSqlMapDao dao = new ItemSqlMapDao(null);
		
		assertFalse("Passed", dao.isItemInStockTG("EST-2"));
	}

}
