package tech.simter.jackson.jsonb;

import java.util.ArrayList;
import java.util.List;

/**
 * A test object class.
 *
 * @author RJ
 */
public class Example {
  private int anInt;
  private String str;
  private List<String> list = new ArrayList<>();

  public int getAnInt() {
    return anInt;
  }

  public void setAnInt(int anInt) {
    this.anInt = anInt;
  }

  public String getStr() {
    return str;
  }

  public void setStr(String str) {
    this.str = str;
  }

  public List<String> getList() {
    return list;
  }

  public void setList(List<String> list) {
    this.list = list;
  }
}