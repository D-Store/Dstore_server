package gg.jominsubyungsin.lib;

public class PageEnd {
	public Boolean pageEnd(int pageSize, int pageNum, Long ColumNum){
		if ((long) (pageNum + 1) * pageSize >= ColumNum) {
			return true;
		}else{
			return false;
		}
	}
}
