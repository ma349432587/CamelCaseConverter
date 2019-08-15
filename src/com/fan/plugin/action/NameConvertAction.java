package com.fan.plugin.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.CaretModel;
import com.intellij.openapi.editor.Editor;
import java.util.List;
import org.apache.commons.lang.StringUtils;


/**
 * CamelCaseConvert简介
 *
 * @author maxiaoyao
 * @date 2019-08-15 18:57
 */
public class NameConvertAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {

        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (null == editor) {
            return;
        }
        CaretModel caretModel = editor.getCaretModel();
        List<Caret> carets = caretModel.getAllCarets();

        Runnable runnable = () -> {
            for (Caret caret : carets) {
                String selectedText = caret.getSelectedText();
                if (StringUtils.isBlank(selectedText)) {
                    editor.getSelectionModel().selectWordAtCaret(true);
                    selectedText = editor.getSelectionModel().getSelectedText();
                }
                String resultText = ConvertUtil.getResult(selectedText);
                editor.getDocument().replaceString(caret.getSelectionStart(), caret.getSelectionEnd(), resultText);
            }
        };

        WriteCommandAction.runWriteCommandAction(e.getData(PlatformDataKeys.PROJECT), runnable);
    }
}
