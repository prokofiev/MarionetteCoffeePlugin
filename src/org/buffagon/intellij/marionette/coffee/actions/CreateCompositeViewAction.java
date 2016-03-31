package org.buffagon.intellij.marionette.coffee.actions;

import com.intellij.ide.IdeView;
import com.intellij.ide.util.DirectoryChooserUtil;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import icons.MarionetteCoffeeIcons;
import org.buffagon.intellij.marionette.coffee.FileSystemWorker;
import org.buffagon.intellij.marionette.coffee.FileUtils;
import org.buffagon.intellij.marionette.coffee.MarionetteCoffeeConstants;
import org.buffagon.intellij.marionette.coffee.Processor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.net.URL;

/**
 * @author Prokofiev Alex
 */
public class CreateCompositeViewAction extends AnAction {
  private static final Logger LOG = Logger.getInstance(CreateCompositeViewAction.class.getName());

  @Override
  public void actionPerformed(@NotNull AnActionEvent e) {
    final IdeView view = e.getData(LangDataKeys.IDE_VIEW);
    if (view == null || e.getProject() == null)
      return;

    final Project project = e.getData(CommonDataKeys.PROJECT);
    final PsiDirectory directory = DirectoryChooserUtil.getOrChooseDirectory(view);
    if (directory == null)
      return;

    String name = Messages.showInputDialog(project, "Creating new composite view",
        "Name", MarionetteCoffeeIcons.COMPOSITE_16, MarionetteCoffeeConstants.COMPOSITE_VIEW, null);
    if (name == null)
      return;

    final String path = directory.getVirtualFile().getPath();


    final String lowerName = Character.toLowerCase(name.charAt(0)) + name.substring(1);
    final String upperName = Character.toUpperCase(name.charAt(0)) + name.substring(1);


    if (!createCompositeViewStructure(path, upperName, lowerName, e.getProject()))
      return;

    LocalFileSystem.getInstance().refreshWithoutFileWatcher(false);
    PsiDirectory componentDir = directory.findSubdirectory(lowerName);
    if (componentDir == null)
      return;

    PsiFile viewFile = componentDir.findFile(lowerName + MarionetteCoffeeConstants.COMPOSITE_VIEW_COFFEE);
    if (viewFile != null)
      view.selectElement(viewFile);
  }

  @SuppressWarnings("Duplicates")
  private boolean createCompositeViewStructure(@NotNull final String path, @NotNull final String upperName,
                                               @NotNull final String lowerName,
                                               @NotNull final Project project) {
    try {
      final String targetPath = path + File.separator + lowerName;
      File f = new File(targetPath);
      if (f.exists()) {
        ApplicationManager.getApplication().invokeLater(new Runnable() {
          @Override
          public void run() {
            Notification notification = MarionetteCoffeeConstants.MARIONETTE_COFFEE_NOTIFICATION_GROUP.createNotification(
                "Directory already exists.", NotificationType.ERROR);
            Notifications.Bus.notify(notification, project);
          }
        });
        return false;
      } else {
        f.mkdirs();
      }
      URL url = CreateCompositeViewAction.class.getClassLoader().getResource("templates/composite_view/");
      FileUtils.copyResourcesRecursively(url, f, false);
      final Processor<String, String> processor = new Processor<String, String>() {
        @Override
        public String process(String value) {
          value = value.replace(MarionetteCoffeeConstants.TEMPLATE_FILENAME_LOWER, lowerName);
          return value.replace(MarionetteCoffeeConstants.TEMPLATE_FILENAME_UPPER, upperName);
        }
      };
      FileSystemWorker.processTextFilesRecursively(f, processor);
      FileSystemWorker.processFileNamesRecursively(f, processor);
    } catch (Exception e) {
      LOG.error(e);
      return false;
    }
    return true;
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    boolean enabled = isEnabled(e);
    e.getPresentation().setVisible(enabled);
    e.getPresentation().setEnabled(enabled);
  }

  private static boolean isEnabled(AnActionEvent e) {
    Project project = e.getData(CommonDataKeys.PROJECT);

    final IdeView ideView = e.getData(LangDataKeys.IDE_VIEW);
    if (project == null || ideView == null)
      return false;

    final PsiDirectory[] directories = ideView.getDirectories();

    return directories.length == 1;
  }
}
