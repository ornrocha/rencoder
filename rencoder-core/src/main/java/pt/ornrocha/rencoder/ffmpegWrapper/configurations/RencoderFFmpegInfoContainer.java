package pt.ornrocha.rencoder.ffmpegWrapper.configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;
import org.tinylog.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.VideoCodecs;
import pt.ornrocha.rencoder.helpers.IndexedHashMap;
import pt.ornrocha.rencoder.helpers.osystem.OSystem;
import pt.ornrocha.rencoder.helpers.props.fields.StaticGlobalFields;

public class RencoderFFmpegInfoContainer {

  private String ffmpegVersion;
  private IndexedHashMap<String, String> ffmpegVideoEncoders;
  private IndexedHashMap<String, String> ffmpegAudioEncoders;
  private ArrayList<String> hwaccelSupportedVideoCodecs = new ArrayList<String>();
  private ArrayList<String> hwaccelDecoders = new ArrayList<String>();
  private VideoCodecs[] rencoderSupportedVideoCodecs = null;



  public String getFFmpegVersion() {
    return ffmpegVersion;
  }

  public void setFFmpegVersion(String ffmpegVersion) {
    this.ffmpegVersion = ffmpegVersion;
  }

  public IndexedHashMap<String, String> getFFmpegVideoEncoders() {
    return ffmpegVideoEncoders;
  }

  public void setFFmpegVideoEncoders(IndexedHashMap<String, String> ffmpegVideoEncoders) {
    this.ffmpegVideoEncoders = ffmpegVideoEncoders;
  }

  public IndexedHashMap<String, String> getFFmpegAudioEncoders() {
    return ffmpegAudioEncoders;
  }

  public void setFFmpegAudioEncoders(IndexedHashMap<String, String> ffmpegAudioEncoders) {
    this.ffmpegAudioEncoders = ffmpegAudioEncoders;
  }

  public ArrayList<String> getHwaccelSupportedVideoCodecs() {
    return hwaccelSupportedVideoCodecs;
  }

  public void setHwaccelSupportedVideoCodecs(ArrayList<String> supportedCodecs) {
    this.hwaccelSupportedVideoCodecs = supportedCodecs;
  }

  public void addHwaccelSupportedVideoCodec(String supportedCodec) {
    this.hwaccelSupportedVideoCodecs.add(supportedCodec);
  }

  public ArrayList<String> getHwaccelDecoders() {
    return hwaccelDecoders;
  }

  public void setHwaccelDecoders(ArrayList<String> hwaccelDecoders) {
    this.hwaccelDecoders = hwaccelDecoders;
  }

  public void addSupportedHwaccelDecoder(String supporteddecoder) {
    this.hwaccelDecoders.add(supporteddecoder);
  }

  public VideoCodecs[] getRencoderSupportedVideoCodecs() {
    return rencoderSupportedVideoCodecs;
  }

  public void setRencoderSupportedVideoCodecs(VideoCodecs[] supportedVideoCodecs) {
    this.rencoderSupportedVideoCodecs = supportedVideoCodecs;
  }



  public static RencoderFFmpegInfoContainer getSavedFFmpegInfo() {

    String jsonfilepath =
        FilenameUtils.concat(OSystem.getCurrentDir(), StaticGlobalFields.FFMPEGSAVEDINFO);

    if (new File(jsonfilepath).exists()) {

      try {
        ObjectMapper mapper = new ObjectMapper();
        InputStream fileInputStream = new FileInputStream(jsonfilepath);
        RencoderFFmpegInfoContainer container =
            mapper.readValue(fileInputStream, RencoderFFmpegInfoContainer.class);
        fileInputStream.close();
        return container;
      } catch (Exception e) {
        Logger.error(e);
        return null;
      }
    } else
      return null;
  }


  public static void writeRencoderFFmpegInfo(RencoderFFmpegInfoContainer container)
      throws JsonGenerationException, JsonMappingException, IOException {

    ObjectMapper mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);


    // Save JSON string to file
    FileOutputStream fileOutputStream = new FileOutputStream(
        FilenameUtils.concat(OSystem.getCurrentDir(), StaticGlobalFields.FFMPEGSAVEDINFO));
    mapper.writeValue(fileOutputStream, container);
    fileOutputStream.close();
  }



}
