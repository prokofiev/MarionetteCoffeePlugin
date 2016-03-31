package org.buffagon.intellij.marionette.coffee;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Prokofiev Alex
 */
public final class MarionetteCoffeeConstants {
  public static final String TEMPLATE_FILENAME_UPPER = "__File_name__";
  public static final String TEMPLATE_FILENAME_LOWER = "__file_name__";

  public static final String TEMPLATE_MODULENAME_UPPER = "__Module_name__";
  public static final String TEMPLATE_MODULENAME_LOWER = "__module_name__";


  public static final String COMPOSITE_VIEW_COFFEE = "CompositeView.coffee";
  public static final String COMPOSITE_VIEW = "CompositeView";

  public static final String ITEM_VIEW_COFFEE = "ItemView.coffee";
  public static final String ITEM_VIEW = "ItemView";

  public static final String MODULE = "module";
  public static final String CONFIG_COFFEE = "config.coffee";




  public static final NotificationGroup MARIONETTE_COFFEE_NOTIFICATION_GROUP =
      new NotificationGroup("Marionette coffee notifications", NotificationDisplayType.BALLOON, true);
}
