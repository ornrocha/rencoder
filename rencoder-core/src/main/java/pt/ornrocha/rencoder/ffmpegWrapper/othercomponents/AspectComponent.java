/*
 * Copyright 2014
 *
 * This is free software: you can redistribute it and/or modify it under the terms of the GNU Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 * 
 * This code is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Public License for more details.
 * 
 * You should have received a copy of the GNU Public License along with this code. If not, see
 * http://www.gnu.org/licenses/
 * 
 * Created by Orlando Rocha
 */
package pt.ornrocha.rencoder.ffmpegWrapper.othercomponents;

import pt.ornrocha.rencoder.ffmpegWrapper.enumerators.video.AspectRatio;

// TODO: Auto-generated Javadoc
/**
 * The Class AspectComponent.
 */
public class AspectComponent {

  /** The name aspect. */
  private String nameAspect = "";

  /** The size measure. */
  private String sizemesu = "";

  /** The ratio. */
  private AspectRatio ratio = null;

  /** The sameassource. */
  public static String SAMEASSOURCE = "Same as source";

  /**
   * Instantiates a new aspect component.
   */
  public AspectComponent() {
    this.nameAspect = SAMEASSOURCE;
    this.sizemesu = "";
  }

  /**
   * Instantiates a new aspect component.
   *
   * @param size the size
   */
  public AspectComponent(String size) {
    this.nameAspect = "Custom";
    this.sizemesu = size;
  }

  /**
   * Instantiates a new aspect component.
   *
   * @param name the name
   * @param size the size
   */
  public AspectComponent(String name, String size) {
    this.nameAspect = name;
    this.sizemesu = size;
  }

  /**
   * Instantiates a new aspect component.
   *
   * @param name the name
   * @param size the size
   * @param ratio the ratio
   */
  public AspectComponent(String name, String size, AspectRatio ratio) {
    this.nameAspect = name;
    this.sizemesu = size;
    this.ratio = ratio;
  }

  /**
   * Gets the name aspect.
   *
   * @return the name aspect
   */
  public String getNameAspect() {
    return nameAspect;
  }

  /**
   * Sets the name aspect.
   *
   * @param nameAspect the new name aspect
   */
  public void setNameAspect(String nameAspect) {
    this.nameAspect = nameAspect;
  }

  /**
   * Gets the size measure.
   *
   * @return the sizemesu
   */
  public String getSizemesu() {
    return sizemesu;
  }

  /**
   * Sets the size measure.
   *
   * @param sizemesu the new sizemesu
   */
  public void setSizemesu(String sizemesu) {
    this.sizemesu = sizemesu;
  }

  /**
   * Gets the video aspect.
   *
   * @return the video aspect
   */
  public String getVideoAspect() {
    if (this.sizemesu == "")
      return this.nameAspect;
    else if (this.nameAspect == "")
      return this.sizemesu;

    return this.nameAspect + " " + "(" + this.sizemesu + ")";
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  public String toString() {
    if (this.sizemesu == "")
      return this.nameAspect;
    else if (this.nameAspect == "")
      return this.sizemesu;

    return this.nameAspect + " " + "(" + this.sizemesu + ")";

  }

  /**
   * Gets the aspect ratio.
   *
   * @return the aspect ratio
   */
  public AspectRatio getAspectRatio() {
    return this.ratio;
  }

  /**
   * Sets the aspect ratio.
   *
   * @param ratio the new aspect ratio
   */
  public void setAspectRatio(AspectRatio ratio) {
    this.ratio = ratio;
  }

}
