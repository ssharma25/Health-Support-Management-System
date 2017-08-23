package model;

import java.util.List;



public interface DataBean<T> {
	
	public boolean add(T addObj);
	public boolean update(T updateObj);
	public List<T> getByUser(T listObj);
	public boolean delete(T object);

}
