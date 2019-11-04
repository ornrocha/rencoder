package pt.ornrocha.rencoder.mediafiles.files.containers.extrainfocontainer;

import java.util.ArrayList;

import org.apache.commons.configuration.PropertiesConfiguration;

public interface IExtraInfoContainer {

    public void saveConfigurationToFileProperties(PropertiesConfiguration prop);
    public ArrayList<String> getFFmpegCmds();
    public IExtraInfoContainer clone();
}
