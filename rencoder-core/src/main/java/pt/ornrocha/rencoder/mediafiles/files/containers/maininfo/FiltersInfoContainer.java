package pt.ornrocha.rencoder.mediafiles.files.containers.maininfo;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import pt.ornrocha.rencoder.ffmpegWrapper.filters.CropFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.IFFmpegFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.RotateFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.ScalingFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceFilter;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceKERNDEINT;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceW3FDIF;
import pt.ornrocha.rencoder.ffmpegWrapper.filters.deinterlace.DeinterlaceYADIF;

public class FiltersInfoContainer {
	
	private LinkedHashSet<IFFmpegFilter> filters=null;
	
	private ScalingFilter scaling = null;
	private CropFilter crop =null;
	private DeinterlaceFilter deinterlace=null;
	private RotateFilter rotate=null;
	
    public FiltersInfoContainer(){
    	filters=new LinkedHashSet<>();
    }

	public LinkedHashSet<IFFmpegFilter> getFilters() {
		return filters;
	}
	
	public ArrayList<String> getFFmpegCmds(){
		ArrayList<String> cmds = null;
		
		if(filters!=null){
			
			if(filters.size()>0){
				cmds = new ArrayList<>();
				for (IFFmpegFilter filter : filters) {
					cmds.add(filter.getFFmpegCMD());
				}
				
			}
		}
		
		return cmds;
	}

	public void setFilters(LinkedHashSet<IFFmpegFilter> filters) {
		this.filters = filters;
	}

	public ScalingFilter getScaling() {
		return scaling;
	}

	public void setScaling(ScalingFilter scaling) {
		if(scaling!=null){
		this.scaling = scaling;
		removeoldobjecttype(ScalingFilter.class);
		filters.add(scaling);
		}
		else{
			removeoldobjecttype(ScalingFilter.class);
			this.scaling=null;
		}
	}

	public CropFilter getCrop() {
		return crop;
	}

	public void setCrop(CropFilter crop) {
		if(crop!=null){
		this.crop = crop;
		removeoldobjecttype(CropFilter.class);
		filters.add(crop);
		}
		else{
			removeoldobjecttype(CropFilter.class);
		    this.crop=null;	
		}
		
	}
	
	public DeinterlaceFilter getDeinterlace() {
		return deinterlace;
	}

	public void setDeinterlace(DeinterlaceFilter deinterlace) {
		if(deinterlace!=null){
		this.deinterlace = deinterlace;
		removeDeinterlaceObjType();
		filters.add(deinterlace);
		}
		else{
			removeDeinterlaceObjType();
			this.deinterlace=null;
		}
	}
	
	
	public RotateFilter getRotate() {
		return rotate;
	}

	public void setRotate(RotateFilter rotate) {
		if(rotate!=null){
		this.rotate = rotate;
		removeoldobjecttype(RotateFilter.class);
		filters.add(rotate);
		}
		else{
			removeoldobjecttype(RotateFilter.class);
			this.rotate=null;
		}
	}
	
	private void removeDeinterlaceObjType(){
		LinkedHashSet<IFFmpegFilter> newfilters = new LinkedHashSet<>();
		
		for (Object element : filters) {
			if(!element.getClass().getSimpleName().equals(DeinterlaceYADIF.class.getSimpleName()) && 
			   !element.getClass().getSimpleName().equals(DeinterlaceW3FDIF.class.getSimpleName()) && 
			   !element.getClass().getSimpleName().equals(DeinterlaceKERNDEINT.class.getSimpleName())){
				newfilters.add((IFFmpegFilter) element);	
			}
		}
		System.out.println(newfilters);
		this.filters=newfilters;
	}
	
	
	private void removeoldobjecttype(Class objtype){
		LinkedHashSet<IFFmpegFilter> newfilters = new LinkedHashSet<>();
		for (Object element : filters) {
			if(!element.getClass().getSimpleName().equals(objtype.getSimpleName()))
				newfilters.add((IFFmpegFilter) element);
		}
		
		this.filters=newfilters;
	}
    
    
    
 
}
