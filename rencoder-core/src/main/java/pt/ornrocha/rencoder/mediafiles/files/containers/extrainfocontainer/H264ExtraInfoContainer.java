package pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer;

import java.util.ArrayList;

import org.apache.commons.configuration.PropertiesConfiguration;

import pt.ornrocha.rencoder.helpers.props.managers.auxiliar.EncodingPropsAuxiliar;

public class H264ExtraInfoContainer implements IExtraInfoContainer{


    protected int keyint=250;
    protected int minkeyint=25; 
    protected int scenecut=40;
    protected int bframes=16;
    protected int badapt=1; // 0,1,2
    protected boolean bpyramid=false;
    protected boolean nocabac=false; //CABAC is not allowed in Baseline Profile
    protected int ref=6; // max 16
    protected boolean nodeblock=false;
    protected int deblockalpha=0;
    protected int deblockbeta=0;
    protected int maxrate=0;
    protected int minrate=0;
    protected int bufsize=0;
    protected int qmin=10;
    protected int qmax=51;
    protected int qdiff=4;
    protected String partitions="none";
    protected int directpred=0;
    protected String memethod="none";
    protected int subq=6;
    protected int trellis=0; 

    public static String keyinttag="-g";
    public static String minkeyinttag="-keyint_min"; 
    public static String scenecuttag="-sc_threshold";
    public static String bframestag="-bf";
    public static String badapttag="-b_strategy";
    public static String bpyramidtag1="-flags2";
    public static String bpyramidtag2="+bpyramid";
    public static String nocabactag ="-coder";
    public static String refstag="-refs";
    public static String nodeblocktag1="-flags";
    public static String nodeblocktag2="-loop";
    public static String deblockalphatag="-deblockalpha";
    public static String deblockbetatag="-deblockbeta";
    public static String maxratetag="-maxrate";
    public static String minratetag="-minrate"; 
    public static String bufsizetag="-bufsize";
    public static String qmintag="-qmin";
    public static String qmaxtag="-qmax"; 
    public static String qdifftag="-qdiff";
    public static String bttag="-bt";
    public static String iqfactortag="-i_qfactor";
    public static String partitionstag="-partitions";
    public static String directpredtag="-directpred";
    public static String memethodtag="-me_method";
    public static String subqtag="-subq";
    public static String trellistag="-trellis"; 


    public H264ExtraInfoContainer(){
	setDefaultValues();
    }






    public H264ExtraInfoContainer(int keyint, int minkeyint, int scenecut,
	    int bframes, int badapt, boolean bpyramid, boolean nocabac,
	    int ref, boolean nodeblock, int deblockalpha, int deblockbeta,
	    int maxrate, int minrate, int bufsize, int qmin, int qmax,
	    int qdiff, String partitions,int directpred,String memethod,int subq,int trellis) {
	super();
	this.keyint = keyint;
	this.minkeyint = minkeyint;
	this.scenecut = scenecut;
	this.bframes = bframes;
	this.badapt = badapt;
	this.bpyramid = bpyramid;
	this.nocabac = nocabac;
	this.ref = ref;
	this.nodeblock = nodeblock;
	this.deblockalpha = deblockalpha;
	this.deblockbeta = deblockbeta;
	this.maxrate = maxrate;
	this.minrate = minrate;
	this.bufsize = bufsize;
	this.qmin = qmin;
	this.qmax = qmax;
	this.qdiff = qdiff;
	this.partitions=partitions;
	this.directpred=directpred;
	this.memethod=memethod;
	this.subq=subq;
	this.trellis=trellis;
    }






    public int getKeyint() {
	return keyint;
    }


    public void setKeyint(int keyint) {
	this.keyint = keyint;
    }


    public int getMinkeyint() {
	return minkeyint;
    }


    public void setMinkeyint(int minkeyint) {
	this.minkeyint = minkeyint;
    }


    public int getScenecut() {
	return scenecut;
    }


    public void setScenecut(int scenecut) {
	this.scenecut = scenecut;
    }


    public int getBframes() {
	return bframes;
    }


    public void setBframes(int bframes) {
	this.bframes = bframes;
    }


    public int getBadapt() {
	return badapt;
    }


    public void setBadapt(int badapt) {
	this.badapt = badapt;
    }


    public boolean isBpyramid() {
	return bpyramid;
    }


    public void setBpyramid(boolean bpyramid) {
	this.bpyramid = bpyramid;
    }


    public boolean isNocabac() {
	return nocabac;
    }


    public void setNocabac(boolean nocabac) {
	this.nocabac = nocabac;
    }


    public int getRef() {
	return ref;
    }


    public void setRef(int ref) {
	this.ref = ref;
    }


    public boolean isNodeblock() {
	return nodeblock;
    }


    public void setNodeblock(boolean nodeblock) {
	this.nodeblock = nodeblock;
    }


    public int getDeblockalpha() {
	return deblockalpha;
    }


    public void setDeblockalpha(int deblockalpha) {
	this.deblockalpha = deblockalpha;
    }


    public int getDeblockbeta() {
	return deblockbeta;
    }


    public void setDeblockbeta(int deblockbeta) {
	this.deblockbeta = deblockbeta;
    }


    public int getMaxrate() {
	return maxrate;
    }


    public void setMaxrate(int maxrate) {
	this.maxrate = maxrate;
    }


    public int getMinrate() {
	return minrate;
    }


    public void setMinrate(int minrate) {
	this.minrate = minrate;
    }


    public int getBufsize() {
	return bufsize;
    }


    public void setBufsize(int bufsize) {
	this.bufsize = bufsize;
    }


    public int getQmin() {
	return qmin;
    }


    public void setQmin(int qmin) {
	this.qmin = qmin;
    }


    public int getQmax() {
	return qmax;
    }


    public void setQmax(int qmax) {
	this.qmax = qmax;
    }


    public int getQdiff() {
	return qdiff;
    }


    public void setQdiff(int qdiff) {
	this.qdiff = qdiff;
    }


    public String getPartitions() {
	return partitions;
    }

    public void setPartitions(String partitions) {
	this.partitions = partitions;
    }

    public int getDirectpred() {
	return directpred;
    }

    public void setDirectpred(int directpred) {
	this.directpred = directpred;
    }

    public String getMemethod() {
	return memethod;
    }

    public void setMemethod(String memethod) {
	this.memethod = memethod;
    }

    public int getSubq() {
	return subq;
    }

    public void setSubq(int subq) {
	this.subq = subq;
    }

    public int getTrellis() {
	return trellis;
    }

    public void setTrellis(int trellis) {
	this.trellis = trellis;
    }


    public ArrayList<String> getFFmpegCmds(){
	ArrayList<String> cmds = new ArrayList<>();

	if(keyint!=250 && keyint>=0){
	    cmds.add(keyinttag);
	    cmds.add(String.valueOf(keyint));
	}
	if(minkeyint!=25 && minkeyint>=0){
	    cmds.add(minkeyinttag);
	    cmds.add(String.valueOf(minkeyint));
	}
	if(scenecut!=40 && scenecut>=0){
	    cmds.add(scenecuttag);
	    cmds.add(String.valueOf(scenecut));
	}
	if(bframes!=16 && bframes>=0){
	    cmds.add(bframestag);
	    cmds.add(String.valueOf(bframes));
	}
	if(badapt!=1 && badapt>=0 && badapt<=2){
	    cmds.add(badapttag);
	    cmds.add(String.valueOf(badapt));
	}
	if(bpyramid){
	    cmds.add(bpyramidtag1);
	    cmds.add(bpyramidtag2);
	}
	if(nocabac){
	    cmds.add(nocabactag);
	    cmds.add("0");
	}
	if(ref!=6 && ref>=0 && ref<=16){
	    cmds.add(refstag);
	    cmds.add(String.valueOf(ref));
	}
	if(nodeblock){
	    cmds.add(nodeblocktag1);
	    cmds.add(nodeblocktag2);
	}
	if(!nodeblock){
	    if(deblockalpha>0){
		cmds.add(deblockalphatag);
		cmds.add(String.valueOf(deblockalpha));
	    }
	    if(deblockbeta>0){
		cmds.add(deblockbetatag);
		cmds.add(String.valueOf(deblockbeta));
	    }
	}
	if(bufsize>0){
	    cmds.add(bufsizetag);
	    cmds.add(EncodingPropsAuxiliar.getBitrateValue(String.valueOf(bufsize)));

	    if(maxrate>0 && maxrate>minrate){
		cmds.add(maxratetag);
		cmds.add(EncodingPropsAuxiliar.getBitrateValue(String.valueOf(maxrate)));
	    }

	    if(minrate>0 && minrate<maxrate){
		cmds.add(minratetag);
		cmds.add(EncodingPropsAuxiliar.getBitrateValue(String.valueOf(minrate)));
	    }
	}

	if(qdiff!=4 && qdiff>-1){
	    cmds.add(qdifftag);
	    cmds.add(String.valueOf(qdiff));
	}
	if(qmin!=10 && qmin<qmax && qmin>0){
	    cmds.add(qmintag);
	    cmds.add(String.valueOf(qmin));
	}
	if(qmax!=51 && qmax>qmin && qmax>0){
	    cmds.add(qmaxtag);
	    cmds.add(String.valueOf(qmax));
	}
	if(!partitions.equals("none")){
	    cmds.add(partitionstag);
	    cmds.add(partitions);
	}
	if(!memethod.equals("none")){
	    cmds.add(memethodtag);
	    cmds.add(memethod);
	}
	if(subq!=6 && subq>0 && subq<=9){
	    cmds.add(subqtag);
	    cmds.add(String.valueOf(subq));
	}
	if(trellis!=0 && trellis<=2){
	    cmds.add(trellistag);
	    cmds.add(String.valueOf(trellis));
	}
	if(directpred!=0){
	    cmds.add(directpredtag);
	    cmds.add(String.valueOf(directpred));
	}




	return cmds;
    }


    public void setDefaultValues(){
	this.keyint=250;
	this.minkeyint=25;

	this.scenecut=40;
	this.bframes=16;
	this.badapt=1; // 0,1,2
	this.bpyramid=false;
	this.nocabac=false; //CABAC is not allowed in Baseline Profile
	this.ref=6; // max 16
	this.nodeblock=false;
	this.deblockalpha=0;
	this.deblockbeta=0;
	this.maxrate=-1;
	this.minrate=-1;
	this.bufsize=-1;
	this.qmin=10;
	this.qmax=51;
	this.qdiff=4;
	this.partitions="none";
	this.directpred=0;
	this.memethod="none";
	this.subq=6;
	this.trellis=0;
    }

    public IExtraInfoContainer clone(){
	return new H264ExtraInfoContainer(keyint, minkeyint, scenecut, bframes, 
		badapt, bpyramid, nocabac, ref, nodeblock, deblockalpha, deblockbeta, 
		maxrate, minrate, bufsize, qmin, qmax, qdiff,partitions,
		directpred,memethod,subq,trellis);
    }

    /**
     *Save Configurations to properties file 
     * 
     * 
     */
    public static final String KEYINT="h264.keyint";
    public static final String MINKEYINT="h264.minkeyint";
    public static final String SCENECUT="h264.scenecut";
    public static final String BFRAMES="h264.bframes";
    public static final String BADAPT="h264.badapt";
    public static final String BPYRAMID="h264.bpyramid";
    public static final String NOCABAC="h264.nocabac";
    public static final String REF="h264.ref";
    public static final String NODEBLOCK="h264.nodeblock";
    public static final String DEBLOCKALPHA="h264.deblockalpha";
    public static final String DEBLOCKBETA="h264.deblockbeta";
    public static final String BUFSIZE="h264.bufsize";
    public static final String MINRATE="h264.minrate";
    public static final String MAXRATE="h264.maxrate";
    public static final String QDIFF="h264.qdiff";
    public static final String QMIN="h264.qmin";
    public static final String QMAX="h264.qmax";
    public static final String PARTITIONS="h264.partitions";
    public static final String DIRECTPRED="h264.directpred";
    public static final String MEMETHOD="h264.memethod";
    public static final String SUBQ="h264.subq";
    public static final String TRELLIS="h264.trellis";


    @Override
    public void saveConfigurationToFileProperties(PropertiesConfiguration prop){

	if(keyint!=250 && keyint>=0){
	    prop.setProperty(KEYINT,String.valueOf(keyint));
	}
	if(minkeyint!=25 && minkeyint>=0){
	    prop.setProperty(MINKEYINT,String.valueOf(minkeyint));
	}
	if(scenecut!=40 && scenecut>=0){
	    prop.setProperty(SCENECUT,String.valueOf(scenecut));
	}
	if(bframes!=16 && bframes>=0){
	    prop.setProperty(BFRAMES,String.valueOf(bframes));
	}
	if(badapt!=1 && badapt>=0 && badapt<=2){
	    prop.setProperty(BADAPT,String.valueOf(badapt));
	}
	if(bpyramid){
	    prop.setProperty(BPYRAMID,String.valueOf(bpyramid));
	}
	if(nocabac){
	    prop.setProperty(NOCABAC,String.valueOf(nocabac));
	}
	if(ref!=6 && ref>=0 && ref<=16){
	    prop.setProperty(REF,String.valueOf(ref));
	}
	if(nodeblock){
	    prop.setProperty(NODEBLOCK,String.valueOf(nodeblock));
	}
	if(!nodeblock){
	    if(deblockalpha>0){
		prop.setProperty(DEBLOCKALPHA,String.valueOf(deblockalpha));
	    }
	    if(deblockbeta>0){
		prop.setProperty(DEBLOCKBETA,String.valueOf(deblockbeta));
	    }
	}
	if(bufsize>0){
	    prop.setProperty(BUFSIZE,String.valueOf(bufsize));

	    if(maxrate>0 && maxrate>minrate){
		prop.setProperty(MAXRATE,String.valueOf(maxrate));
	    }

	    if(minrate>0 && minrate<maxrate){
		prop.setProperty(MINRATE,String.valueOf(minrate));
	    }
	}

	if(qdiff!=4 && qdiff>-1){
	    prop.setProperty(QDIFF,String.valueOf(qdiff));
	}
	if(qmin!=10 && qmin<qmax && qmin>0){
	    prop.setProperty(QMIN,String.valueOf(qmin));
	}
	if(qmax!=51 && qmax>qmin && qmax>0){
	    prop.setProperty(QMAX,String.valueOf(qmax));
	}
	if(!partitions.equals("none")){
	    prop.setProperty(PARTITIONS,String.valueOf(partitions));
	}
	if(!memethod.equals("none")){
	    prop.setProperty(MEMETHOD,String.valueOf(memethod));
	}
	if(subq!=6 && subq>0 && subq<=9){
	    prop.setProperty(SUBQ,String.valueOf(subq));
	}
	if(trellis!=0 && trellis<=2){
	    prop.setProperty(TRELLIS,String.valueOf(trellis));
	}
	if(directpred!=0){
	    prop.setProperty(DIRECTPRED,String.valueOf(directpred));
	}

    }

    /**
     * Load H264 Extra info from properties file
     */

    public static H264ExtraInfoContainer loadH264ExtraInfoContainer(PropertiesConfiguration props){

	H264ExtraInfoContainer newextracont=null;

	if(props.containsKey(KEYINT)||
		props.containsKey(MINKEYINT)||
		props.containsKey(SCENECUT)||
		props.containsKey(BFRAMES)||
		props.containsKey(BADAPT)||
		props.containsKey(BPYRAMID)||
		props.containsKey(NOCABAC)||
		props.containsKey(REF)||
		props.containsKey(NODEBLOCK)||
		props.containsKey(DEBLOCKALPHA)||
		props.containsKey(DEBLOCKBETA)||
		props.containsKey(BUFSIZE)||
		props.containsKey(MINRATE)||
		props.containsKey(MAXRATE)||
		props.containsKey(QDIFF)||
		props.containsKey(QMIN)||
		props.containsKey(QMAX)||
		props.containsKey(PARTITIONS)||
		props.containsKey(DIRECTPRED)||
		props.containsKey(MEMETHOD)||
		props.containsKey(SUBQ)||
		props.containsKey(TRELLIS)){

	    newextracont=getDefaulPropertiesH264ExtraInfoContainer();
	}


	if(newextracont!=null){

	    if(props.containsKey(KEYINT)){
		int keyint=Integer.valueOf((String) props.getProperty(KEYINT));
		newextracont.setKeyint(keyint);
	    }
	    if(props.containsKey(MINKEYINT)){
		int minkeyint=Integer.valueOf((String) props.getProperty(MINKEYINT));
		newextracont.setMinkeyint(minkeyint);
	    }
	    if(props.containsKey(SCENECUT)){
		int scenecut =Integer.valueOf((String) props.getProperty(SCENECUT));
		newextracont.setScenecut(scenecut);
	    }
	    if(props.containsKey(BFRAMES)){
		int bframes =Integer.valueOf((String) props.getProperty(BFRAMES));
		newextracont.setBframes(bframes);
	    }
	    if(props.containsKey(BADAPT)){
		int badapt =Integer.valueOf((String) props.getProperty(BADAPT));
		newextracont.setBadapt(badapt);
	    }
	    if(props.containsKey(BPYRAMID)){
		String bpyramid= (String) props.getProperty(BPYRAMID);
		if(bpyramid.toLowerCase().equals("true"))
		    newextracont.setBpyramid(true);
	    }   
	    if(props.containsKey(NOCABAC)){
		String nocabac= (String) props.getProperty(NOCABAC);
		if(nocabac.toLowerCase().equals("true"))
		    newextracont.setNocabac(true);
	    }
	    if(props.containsKey(REF)){
		int ref =Integer.valueOf((String) props.getProperty(REF));
		newextracont.setRef(ref);
	    }
	    if(props.containsKey(NODEBLOCK)){
		String nodeblock= (String) props.getProperty(NODEBLOCK);
		if(nodeblock.toLowerCase().equals("true"))
		    newextracont.setNodeblock(true);
	    }
	    if(props.containsKey(DEBLOCKALPHA)){
		int deblockalpha =Integer.valueOf((String) props.getProperty(DEBLOCKALPHA));
		newextracont.setDeblockalpha(deblockalpha);
	    }
	    if(props.containsKey(DEBLOCKBETA)){
		int deblockbeta =Integer.valueOf((String) props.getProperty(DEBLOCKBETA));
		newextracont.setDeblockbeta(deblockbeta); 
	    }
	    if(props.containsKey(BUFSIZE)){
		int bufsize =Integer.valueOf((String) props.getProperty(BUFSIZE));
		newextracont.setBufsize(bufsize); 
	    }
	    if(props.containsKey(MINRATE)){
		int minrate =Integer.valueOf((String) props.getProperty(MINRATE));
		newextracont.setMinrate(minrate); 
	    }
	    if(props.containsKey(MAXRATE)){
		int maxrate =Integer.valueOf((String) props.getProperty(MAXRATE));
		newextracont.setMaxrate(maxrate); 
	    }
	    if(props.containsKey(QDIFF)){
		int qdiff =Integer.valueOf((String) props.getProperty(QDIFF));
		newextracont.setQdiff(qdiff); 
	    }
	    if(props.containsKey(QMIN)){
		int qmin =Integer.valueOf((String) props.getProperty(QMIN));
		newextracont.setQmin(qmin); 
	    }
	    if( props.containsKey(QMAX)){
		int qmax =Integer.valueOf((String) props.getProperty(QMAX));
		newextracont.setQmax(qmax); 
	    }
	    if(props.containsKey(PARTITIONS)){
		newextracont.setPartitions((String) props.getProperty(PARTITIONS));
	    }
	    if(props.containsKey(DIRECTPRED)){
		int directpred =Integer.valueOf((String) props.getProperty(DIRECTPRED));
		newextracont.setDirectpred(directpred); 
	    }
	    if(props.containsKey(MEMETHOD)){
		newextracont.setMemethod((String) props.getProperty(MEMETHOD));
	    }
	    if(props.containsKey(SUBQ)){
		int subq =Integer.valueOf((String) props.getProperty(SUBQ));
		newextracont.setSubq(subq);
	    }
	    if(props.containsKey(TRELLIS)){
		int trellis =Integer.valueOf((String) props.getProperty(TRELLIS));
		newextracont.setTrellis(trellis); 
	    }
	}

	return newextracont;
    }

    public static H264ExtraInfoContainer getDefaulPropertiesH264ExtraInfoContainer(){
	return new H264ExtraInfoContainer();
    }



}
