package pt.ornrocha.rencoder.gui.components.panels.auxiliar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListContainer<T extends Object> {
	
	
	
	protected List<T> listobj = null;

	
	
	public ListContainer(List<T> initinput){
		this.listobj= initinput;
	}
	
	
    public void moveElementUp(int index){
		   if(index>0 && listobj.size()>1)
				 Collections.swap(listobj, index, index-1);
	   }
	
    public void moveElementDown(int index){
 	   if(index>=0 && index+1<listobj.size() && listobj.size()>1)
 			 Collections.swap(listobj, index, index+1);
    }
    
    public void insert(T item){
    	this.listobj.add(item);
    }
    
    public void insertItems(List<T> obj){
    	if(listobj!=null){
    		for (T t : obj) {
				listobj.add(t);
			}
    	}
    	else{
    		listobj=obj;
    	}
    }
    
    public void reset(){
    	this.listobj= null;
    }
    
    public void removeElems(int[] pos){
    	if(listobj!=null){
        int a=0;	
    	for (int i = 0; i < pos.length; i++) {
    		int p = pos[i]-a;
    		if(p>-1)
			 listobj.remove(p);
			a++;
		 }
    	}
    }
    
    
    public void removeElem(int pos){
    	if(listobj!=null)
    		listobj.remove(pos);
    }
    
    public int getListSize(){
    	return this.listobj.size();
    }
    
    public List<T> getList(){
    	return this.listobj;
    }
    
    static <T> ArrayList<T> listOf(Class<T> clazz)
    {
        return new ArrayList<T>();
    }
	
	
}
