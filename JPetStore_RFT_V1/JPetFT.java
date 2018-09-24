
import resources.JPetFTHelper;
import com.rational.test.ft.*;
import com.rational.test.ft.object.interfaces.*;
import com.rational.test.ft.object.interfaces.SAP.*;
import com.rational.test.ft.object.interfaces.WPF.*;
import com.rational.test.ft.object.interfaces.dojo.*;
import com.rational.test.ft.object.interfaces.siebel.*;
import com.rational.test.ft.object.interfaces.flex.*;
import com.rational.test.ft.object.interfaces.generichtmlsubdomain.*;
import com.rational.test.ft.script.*;
import com.rational.test.ft.value.*;
import com.rational.test.ft.vp.*;
import com.ibm.rational.test.ft.object.interfaces.sapwebportal.*;
/**
 * Description   : Functional Test Script
 * @author ciplatform
 */
public class JPetFT extends JPetFTHelper
{
	/**
	 * Script Name   : <b>JPetFT</b>
	 * Generated     : <b>May 11, 2018 3:17:52 PM</b>
	 * Description   : Functional Test Script
	 * Original Host : WinNT Version 6.1  Build 7601 (S)
	 * 
	 * @since  2018/05/11
	 * @author ciplatform
	 */
	public void testMain(Object[] args) 
	{
		startApp("JPetStore");
		
		// HTML Browser
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/
		link_enterMyStore().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/index.shtml
		image_sm_fish().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/viewCategory.shtml;jsessionid=406F611CB321DE309B0FF4A222BAF697?categoryId=FISH
		link_fisW02().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/viewProduct.shtml?productId=FI-SW-02
		image_button_add_to_cart().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/addItemToCart.shtml?workingItemId=EST-3
		image_button_checkout().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/checkout.shtml
		image_button_continue().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/newOrderForm.shtml
		button_button_submit().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/signon.shtml
		image_sm_reptiles().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/viewCategory.shtml?categoryId=REPTILES
		link_rpsN01().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/viewProduct.shtml?productId=FI-SW-02
		link_esT11().click();
		// Document: JPetStore Demo: http://vqtools122:7979/JPetStore/shop/viewItem.shtml?itemId=EST-11
		image_button_add_to_cart2().click();
	}
}

