package eis;

import java.math.BigDecimal;

import org.junit.Test;

import com.lj.base.mvc.web.test.SpringTestCase;
import com.lj.eshop.eis.utils.ProductQRcodeUtil;

public class ProductQRcodeUtilTest extends SpringTestCase {

	@Test
	public void qrcode() {
		String url = "http://dv.uxuan.info/dist-em/goodDetail?menu=0&code=LJ_356b0556fdc94fea9ff52a45b3b47c42&shopCode=LJ_0e0a461585fa493794cc1368b87ec324";
		String name = "施华洛世奇Frisson时间简约戒指指环女经典百搭配饰施华洛世奇Frisson时间简约戒指指环女经典百搭配饰";
		// String price="￥6000";
		BigDecimal price = new BigDecimal(8888.4432);
		String key = ProductQRcodeUtil.createShareQRcode(url,
				"/eoms/image/product/000fd0ef-ab45-4eda-91fc-9cee9e1eea62.jpg", name, price);
		System.out.println(key);
	}
}
