import com.ibatis.jpetstore.service.CatalogService;
import com.ibatis.jpetstore.domain.Category;

/**
 * @author daniel
 */
public class GetCategoryWithProductsTest {

    public GetCategoryWithProductsTest() {
    }

    public static void main( String[] args ) {
        new GetCategoryWithProductsTest().test();
    }

    public void test(){
        Category c = CatalogService.getInstance().getCategoryWithProducts( "DOGS" );
        System.out.println( "The category ID we queried for was: " + c.getCategoryId() );
        System.out.println( "It contained the products: " + c.getProducts() );
    }
}
