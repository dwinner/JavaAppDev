/*
* This file is part of WebLookAndFeel library.
*
* WebLookAndFeel library is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* WebLookAndFeel library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.laf;

import com.alee.laf.button.WebButtonUI;
import com.alee.laf.button.WebToggleButtonUI;
import com.alee.laf.checkbox.WebCheckBoxUI;
import com.alee.laf.colorchooser.WebColorChooserUI;
import com.alee.laf.combobox.WebComboBoxUI;
import com.alee.laf.desktoppane.WebDesktopIconUI;
import com.alee.laf.desktoppane.WebDesktopPaneUI;
import com.alee.laf.desktoppane.WebInternalFrameUI;
import com.alee.laf.filechooser.WebFileChooserUI;
import com.alee.laf.label.WebLabelUI;
import com.alee.laf.list.WebListCellRenderer;
import com.alee.laf.list.WebListUI;
import com.alee.laf.menu.*;
import com.alee.laf.optionpane.WebOptionPaneUI;
import com.alee.laf.panel.WebPanelUI;
import com.alee.laf.progressbar.WebProgressBarUI;
import com.alee.laf.radiobutton.WebRadioButtonUI;
import com.alee.laf.rootpane.WebRootPaneUI;
import com.alee.laf.scroll.WebScrollBarUI;
import com.alee.laf.scroll.WebScrollPaneUI;
import com.alee.laf.separator.WebSeparatorUI;
import com.alee.laf.slider.WebSliderUI;
import com.alee.laf.spinner.WebSpinnerUI;
import com.alee.laf.splitpane.WebSplitPaneUI;
import com.alee.laf.tabbedpane.WebTabbedPaneUI;
import com.alee.laf.table.WebTableCorner;
import com.alee.laf.table.WebTableHeaderUI;
import com.alee.laf.table.WebTableUI;
import com.alee.laf.text.*;
import com.alee.laf.toolbar.WebToolBarSeparatorUI;
import com.alee.laf.toolbar.WebToolBarUI;
import com.alee.laf.tooltip.WebToolTipUI;
import com.alee.laf.tree.WebTreeUI;
import com.alee.laf.viewport.WebViewportUI;
import com.alee.managers.focus.FocusManager;
import com.alee.managers.hotkey.HotkeyManager;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;

/**
 * User: mgarin Date: 27.04.11 Time: 17:40
 */

public class WebLookAndFeel extends BasicLookAndFeel
{


    /*
    * Описание стиля
    */

    public String getName ()
    {
        return "WebLookAndFeel";
    }

    public String getID ()
    {
        return "WebLookAndFeel";
    }

    public String getDescription ()
    {
        return "Cross-platform stylish Look and Feel";
    }

    public boolean isNativeLookAndFeel ()
    {
        return false;
    }

    public boolean isSupportedLookAndFeel ()
    {
        return true;
    }

    public boolean getSupportsWindowDecorations ()
    {
        return false;
    }

    /*
    * Инициализация стилей
    */

    public UIDefaults getDefaults ()
    {
        // Инициализируем базовые свойства
        UIDefaults defaults = super.getDefaults ();

        // Инициализируем стилизационные свойства
        initializeStyles ();

        return defaults;
    }

    protected void initClassDefaults ( UIDefaults table )
    {
        // Label
        table.put ( "LabelUI", WebLabelUI.class.getCanonicalName () );
        table.put ( "ToolTipUI", WebToolTipUI.class.getCanonicalName () );

        // Button
        table.put ( "ButtonUI", WebButtonUI.class.getCanonicalName () );
        table.put ( "ToggleButtonUI", WebToggleButtonUI.class.getCanonicalName () );
        table.put ( "CheckBoxUI", WebCheckBoxUI.class.getCanonicalName () );
        table.put ( "RadioButtonUI", WebRadioButtonUI.class.getCanonicalName () );

        // Menu
        table.put ( "MenuBarUI", WebMenuBarUI.class.getCanonicalName () );
        table.put ( "MenuUI", WebMenuUI.class.getCanonicalName () );
        table.put ( "PopupMenuUI", WebPopupMenuUI.class.getCanonicalName () );
        table.put ( "MenuItemUI", WebMenuItemUI.class.getCanonicalName () );
        table.put ( "CheckBoxMenuItemUI", WebCheckBoxMenuItemUI.class.getCanonicalName () );
        table.put ( "RadioButtonMenuItemUI", WebRadioButtonMenuItemUI.class.getCanonicalName () );
        table.put ( "PopupMenuSeparatorUI", WebPopupMenuSeparatorUI.class.getCanonicalName () );

        // Separator
        table.put ( "SeparatorUI", WebSeparatorUI.class.getCanonicalName () );

        // Scroll
        table.put ( "ScrollBarUI", WebScrollBarUI.class.getCanonicalName () );
        table.put ( "ScrollPaneUI", WebScrollPaneUI.class.getCanonicalName () );

        // Text
        table.put ( "TextFieldUI", WebTextFieldUI.class.getCanonicalName () );
        table.put ( "PasswordFieldUI", WebPasswordFieldUI.class.getCanonicalName () );
        table.put ( "FormattedTextFieldUI", WebFormattedTextFieldUI.class.getCanonicalName () );
        table.put ( "TextAreaUI", WebTextAreaUI.class.getCanonicalName () );
        table.put ( "EditorPaneUI", WebEditorPaneUI.class.getCanonicalName () );
        table.put ( "TextPaneUI", WebTextPaneUI.class.getCanonicalName () );

        // Toolbar
        table.put ( "ToolBarUI", WebToolBarUI.class.getCanonicalName () );
        table.put ( "ToolBarSeparatorUI", WebToolBarSeparatorUI.class.getCanonicalName () );

        // Table
        table.put ( "TableUI", WebTableUI.class.getCanonicalName () );
        table.put ( "TableHeaderUI", WebTableHeaderUI.class.getCanonicalName () );

        // Chooser
        table.put ( "ColorChooserUI", WebColorChooserUI.class.getCanonicalName () );
        table.put ( "FileChooserUI", WebFileChooserUI.class.getCanonicalName () );

        // Container
        table.put ( "PanelUI", WebPanelUI.class.getCanonicalName () );
        table.put ( "ViewportUI", WebViewportUI.class.getCanonicalName () );
        table.put ( "RootPaneUI", WebRootPaneUI.class.getCanonicalName () );
        table.put ( "TabbedPaneUI", WebTabbedPaneUI.class.getCanonicalName () );
        table.put ( "SplitPaneUI", WebSplitPaneUI.class.getCanonicalName () );

        // Complex components
        table.put ( "ProgressBarUI", WebProgressBarUI.class.getCanonicalName () );
        table.put ( "SliderUI", WebSliderUI.class.getCanonicalName () );
        table.put ( "SpinnerUI", WebSpinnerUI.class.getCanonicalName () );
        table.put ( "TreeUI", WebTreeUI.class.getCanonicalName () );
        table.put ( "ListUI", WebListUI.class.getCanonicalName () );
        table.put ( "ComboBoxUI", WebComboBoxUI.class.getCanonicalName () );

        // Desktop pane
        table.put ( "DesktopPaneUI", WebDesktopPaneUI.class.getCanonicalName () );
        table.put ( "DesktopIconUI", WebDesktopIconUI.class.getCanonicalName () );
        table.put ( "InternalFrameUI", WebInternalFrameUI.class.getCanonicalName () );

        // Option pane
        table.put ( "OptionPaneUI", WebOptionPaneUI.class.getCanonicalName () );
    }

    protected void initComponentDefaults ( UIDefaults table )
    {
        super.initComponentDefaults ( table );

        // Действия текстового поля
        table.put ( "TextField.focusInputMap", new UIDefaults.LazyInputMap (
                new Object[]{ "control C", DefaultEditorKit.copyAction, "control V",
                        DefaultEditorKit.pasteAction, "control X", DefaultEditorKit.cutAction,
                        "COPY", DefaultEditorKit.copyAction, "PASTE", DefaultEditorKit.pasteAction,
                        "CUT", DefaultEditorKit.cutAction, "control INSERT",
                        DefaultEditorKit.copyAction, "shift INSERT", DefaultEditorKit.pasteAction,
                        "shift DELETE", DefaultEditorKit.cutAction, "control A",
                        DefaultEditorKit.selectAllAction, "control BACK_SLASH", "unselect"
                        /*DefaultEditorKit.unselectAction*/, "shift LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift RIGHT",
                        DefaultEditorKit.selectionForwardAction, "control LEFT",
                        DefaultEditorKit.previousWordAction, "control RIGHT",
                        DefaultEditorKit.nextWordAction, "control shift LEFT",
                        DefaultEditorKit.selectionPreviousWordAction, "control shift RIGHT",
                        DefaultEditorKit.selectionNextWordAction, "HOME",
                        DefaultEditorKit.beginLineAction, "END", DefaultEditorKit.endLineAction,
                        "shift HOME", DefaultEditorKit.selectionBeginLineAction, "shift END",
                        DefaultEditorKit.selectionEndLineAction, "BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "shift BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "ctrl H",
                        DefaultEditorKit.deletePrevCharAction, "DELETE",
                        DefaultEditorKit.deleteNextCharAction, "ctrl DELETE",
                        DefaultEditorKit.deleteNextWordAction, "ctrl BACK_SPACE",
                        DefaultEditorKit.deletePrevWordAction, "RIGHT",
                        DefaultEditorKit.forwardAction, "LEFT", DefaultEditorKit.backwardAction,
                        "KP_RIGHT", DefaultEditorKit.forwardAction, "KP_LEFT",
                        DefaultEditorKit.backwardAction, "ENTER", JTextField.notifyAction,
                        "control shift O", "toggle-componentOrientation"
                        /*DefaultEditorKit.toggleComponentOrientation*/ } ) );

        // Действия поля для пароля
        table.put ( "PasswordField.focusInputMap", new UIDefaults.LazyInputMap (
                new Object[]{ "control C", DefaultEditorKit.copyAction, "control V",
                        DefaultEditorKit.pasteAction, "control X", DefaultEditorKit.cutAction,
                        "COPY", DefaultEditorKit.copyAction, "PASTE", DefaultEditorKit.pasteAction,
                        "CUT", DefaultEditorKit.cutAction, "control INSERT",
                        DefaultEditorKit.copyAction, "shift INSERT", DefaultEditorKit.pasteAction,
                        "shift DELETE", DefaultEditorKit.cutAction, "control A",
                        DefaultEditorKit.selectAllAction, "control BACK_SLASH", "unselect"
                        /*DefaultEditorKit.unselectAction*/, "shift LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift RIGHT",
                        DefaultEditorKit.selectionForwardAction, "control LEFT",
                        DefaultEditorKit.beginLineAction, "control RIGHT",
                        DefaultEditorKit.endLineAction, "control shift LEFT",
                        DefaultEditorKit.selectionBeginLineAction, "control shift RIGHT",
                        DefaultEditorKit.selectionEndLineAction, "HOME",
                        DefaultEditorKit.beginLineAction, "END", DefaultEditorKit.endLineAction,
                        "shift HOME", DefaultEditorKit.selectionBeginLineAction, "shift END",
                        DefaultEditorKit.selectionEndLineAction, "BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "shift BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "ctrl H",
                        DefaultEditorKit.deletePrevCharAction, "DELETE",
                        DefaultEditorKit.deleteNextCharAction, "RIGHT",
                        DefaultEditorKit.forwardAction, "LEFT", DefaultEditorKit.backwardAction,
                        "KP_RIGHT", DefaultEditorKit.forwardAction, "KP_LEFT",
                        DefaultEditorKit.backwardAction, "ENTER", JTextField.notifyAction,
                        "control shift O", "toggle-componentOrientation"
                        /*DefaultEditorKit.toggleComponentOrientation*/ } ) );

        // Действия поля для отформатированного текста
        table.put ( "FormattedTextField.focusInputMap", new UIDefaults.LazyInputMap (
                new Object[]{ "ctrl C", DefaultEditorKit.copyAction, "ctrl V",
                        DefaultEditorKit.pasteAction, "ctrl X", DefaultEditorKit.cutAction, "COPY",
                        DefaultEditorKit.copyAction, "PASTE", DefaultEditorKit.pasteAction, "CUT",
                        DefaultEditorKit.cutAction, "control INSERT", DefaultEditorKit.copyAction,
                        "shift INSERT", DefaultEditorKit.pasteAction, "shift DELETE",
                        DefaultEditorKit.cutAction, "shift LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift KP_LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift RIGHT",
                        DefaultEditorKit.selectionForwardAction, "shift KP_RIGHT",
                        DefaultEditorKit.selectionForwardAction, "ctrl LEFT",
                        DefaultEditorKit.previousWordAction, "ctrl KP_LEFT",
                        DefaultEditorKit.previousWordAction, "ctrl RIGHT",
                        DefaultEditorKit.nextWordAction, "ctrl KP_RIGHT",
                        DefaultEditorKit.nextWordAction, "ctrl shift LEFT",
                        DefaultEditorKit.selectionPreviousWordAction, "ctrl shift KP_LEFT",
                        DefaultEditorKit.selectionPreviousWordAction, "ctrl shift RIGHT",
                        DefaultEditorKit.selectionNextWordAction, "ctrl shift KP_RIGHT",
                        DefaultEditorKit.selectionNextWordAction, "ctrl A",
                        DefaultEditorKit.selectAllAction, "HOME", DefaultEditorKit.beginLineAction,
                        "END", DefaultEditorKit.endLineAction, "shift HOME",
                        DefaultEditorKit.selectionBeginLineAction, "shift END",
                        DefaultEditorKit.selectionEndLineAction, "BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "shift BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "ctrl H",
                        DefaultEditorKit.deletePrevCharAction, "DELETE",
                        DefaultEditorKit.deleteNextCharAction, "ctrl DELETE",
                        DefaultEditorKit.deleteNextWordAction, "ctrl BACK_SPACE",
                        DefaultEditorKit.deletePrevWordAction, "RIGHT",
                        DefaultEditorKit.forwardAction, "LEFT", DefaultEditorKit.backwardAction,
                        "KP_RIGHT", DefaultEditorKit.forwardAction, "KP_LEFT",
                        DefaultEditorKit.backwardAction, "ENTER", JTextField.notifyAction,
                        "ctrl BACK_SLASH", "unselect", "control shift O",
                        "toggle-componentOrientation", "ESCAPE", "reset-field-edit", "UP",
                        "increment", "KP_UP", "increment", "DOWN", "decrement", "KP_DOWN",
                        "decrement", } ) );

        // Действия мультистроковых полей
        Object multilineInputMap = new UIDefaults.LazyInputMap (
                new Object[]{ "control C", DefaultEditorKit.copyAction, "control V",
                        DefaultEditorKit.pasteAction, "control X", DefaultEditorKit.cutAction,
                        "COPY", DefaultEditorKit.copyAction, "PASTE", DefaultEditorKit.pasteAction,
                        "CUT", DefaultEditorKit.cutAction, "control INSERT",
                        DefaultEditorKit.copyAction, "shift INSERT", DefaultEditorKit.pasteAction,
                        "shift DELETE", DefaultEditorKit.cutAction, "shift LEFT",
                        DefaultEditorKit.selectionBackwardAction, "shift RIGHT",
                        DefaultEditorKit.selectionForwardAction, "control LEFT",
                        DefaultEditorKit.previousWordAction, "control RIGHT",
                        DefaultEditorKit.nextWordAction, "control shift LEFT",
                        DefaultEditorKit.selectionPreviousWordAction, "control shift RIGHT",
                        DefaultEditorKit.selectionNextWordAction, "control A",
                        DefaultEditorKit.selectAllAction, "control BACK_SLASH", "unselect"
                        /*DefaultEditorKit.unselectAction*/, "HOME",
                        DefaultEditorKit.beginLineAction, "END", DefaultEditorKit.endLineAction,
                        "shift HOME", DefaultEditorKit.selectionBeginLineAction, "shift END",
                        DefaultEditorKit.selectionEndLineAction, "control HOME",
                        DefaultEditorKit.beginAction, "control END", DefaultEditorKit.endAction,
                        "control shift HOME", DefaultEditorKit.selectionBeginAction,
                        "control shift END", DefaultEditorKit.selectionEndAction, "UP",
                        DefaultEditorKit.upAction, "DOWN", DefaultEditorKit.downAction,
                        "BACK_SPACE", DefaultEditorKit.deletePrevCharAction, "shift BACK_SPACE",
                        DefaultEditorKit.deletePrevCharAction, "ctrl H",
                        DefaultEditorKit.deletePrevCharAction, "DELETE",
                        DefaultEditorKit.deleteNextCharAction, "ctrl DELETE",
                        DefaultEditorKit.deleteNextWordAction, "ctrl BACK_SPACE",
                        DefaultEditorKit.deletePrevWordAction, "RIGHT",
                        DefaultEditorKit.forwardAction, "LEFT", DefaultEditorKit.backwardAction,
                        "KP_RIGHT", DefaultEditorKit.forwardAction, "KP_LEFT",
                        DefaultEditorKit.backwardAction, "PAGE_UP", DefaultEditorKit.pageUpAction,
                        "PAGE_DOWN", DefaultEditorKit.pageDownAction, "shift PAGE_UP",
                        "selection-page-up", "shift PAGE_DOWN", "selection-page-down",
                        "ctrl shift PAGE_UP", "selection-page-left", "ctrl shift PAGE_DOWN",
                        "selection-page-right", "shift UP", DefaultEditorKit.selectionUpAction,
                        "shift DOWN", DefaultEditorKit.selectionDownAction, "ENTER",
                        DefaultEditorKit.insertBreakAction, "TAB", DefaultEditorKit.insertTabAction,
                        "control T", "next-link-action", "control shift T", "previous-link-action",
                        "control SPACE", "activate-link-action", "control shift O",
                        "toggle-componentOrientation"
                        /*DefaultEditorKit.toggleComponentOrientation*/ } );
        table.put ( "TextArea.focusInputMap", multilineInputMap );
        table.put ( "TextPane.focusInputMap", multilineInputMap );
        table.put ( "EditorPane.focusInputMap", multilineInputMap );

        // Действия комбо-бокса
        table.put ( "ComboBox.ancestorInputMap", new UIDefaults.LazyInputMap (
                new Object[]{ "ESCAPE", "hidePopup", "PAGE_UP", "pageUpPassThrough", "PAGE_DOWN",
                        "pageDownPassThrough", "HOME", "homePassThrough", "END", "endPassThrough",
                        "DOWN", "selectNext", "KP_DOWN", "selectNext", "alt DOWN", "togglePopup",
                        "alt KP_DOWN", "togglePopup", "alt UP", "togglePopup", "alt KP_UP",
                        "togglePopup", "SPACE", "spacePopup", "ENTER", "enterPressed", "UP",
                        "selectPrevious", "KP_UP", "selectPrevious" } ) );

        // Действия выборщика файлов
        table.put ( "FileChooser.ancestorInputMap", new UIDefaults.LazyInputMap (
                new Object[]{ "ESCAPE", "cancelSelection", "F2", "editFileName", "F5", "refresh",
                        "BACK_SPACE", "Go Up", "ENTER", "approveSelection", "ctrl ENTER",
                        "approveSelection" } ) );
    }

    public void initialize ()
    {
        super.initialize ();

        // Слушатель ALT для передачи фокуса менюбару
        KeyboardFocusManager.getCurrentKeyboardFocusManager ().
                addKeyEventPostProcessor ( WebRootPaneUI.altProcessor );
    }

    private static boolean isMnemonicHidden = true;

    public static void setMnemonicHidden ( boolean hide )
    {
        isMnemonicHidden = !UIManager.getBoolean ( "Button.showMnemonics" ) && hide;
    }

    public static boolean isMnemonicHidden ()
    {
        if ( UIManager.getBoolean ( "Button.showMnemonics" ) )
        {
            isMnemonicHidden = false;
        }
        return isMnemonicHidden;
    }

    /*
    * Метод инициализации переменных, необходимых для работы отдельных UI.
    * Отдельные UI из WebLookAndFeel возможно использовать после вызова данного метода без
    * непосредственной установки LookAndFeel'а в UIManager'е.
    */

    public static void initializeStyles ()
    {
        // Антиалиасинг текста
        UIManager.put ( SwingUtilities2.AA_TEXT_PROPERTY_KEY,
                SwingUtilities2.AATextInfo.getAATextInfo ( true ) );

        // Измененные иконки дерева
        UIManager.put ( "Tree.collapsedIcon", WebTreeUI.EXPAND_ICON );
        UIManager.put ( "Tree.expandedIcon", WebTreeUI.COLLAPSE_ICON );
        UIManager.put ( "Tree.closedIcon", WebTreeUI.CLOSED_ICON );
        UIManager.put ( "Tree.openIcon", WebTreeUI.OPEN_ICON );
        UIManager.put ( "Tree.leafIcon", WebTreeUI.LEAF_ICON );

        // Цвет гриппера WebSplitPane при перетаскивании
        UIManager.put ( "SplitPaneDivider.draggingColor", Color.LIGHT_GRAY );

        // Шрифт акселлератора элемента меню
        UIManager.put ( "MenuItem.acceleratorFont", new Font ( "Arial", Font.PLAIN, 11 ) );
        // Цвет заблокированного текста элемента меню
        UIManager.put ( "MenuItem.disabledForeground", StyleConstants.disabledTextColor );

        // Отступы расположения открывающегося попап-меню у WebMenu
        // При разворачивании вправо-влево
        UIManager.put ( "Menu.submenuPopupOffsetX", StyleConstants.shadeWidth );
        UIManager.put ( "Menu.submenuPopupOffsetY", -1 );
        // При разворачивании вниз
        UIManager.put ( "Menu.menuPopupOffsetX", StyleConstants.shadeWidth );
        UIManager.put ( "Menu.menuPopupOffsetY", -StyleConstants.shadeWidth / 2 );

        // Правый верхний угол таблицы
        UIManager.put ( "Table.scrollPaneCornerComponent", WebTableCorner.class );

        // Цвета для таблицы
        UIManager.put ( "Table.cellNoFocusBorder", BorderFactory.createEmptyBorder ( 1, 1, 1, 1 ) );
        UIManager.put ( "Table.focusSelectedCellHighlightBorder",
                BorderFactory.createEmptyBorder ( 1, 1, 1, 1 ) );
        UIManager.put ( "Table.focusCellHighlightBorder",
                BorderFactory.createEmptyBorder ( 1, 1, 1, 1 ) );

        // Отступы от краёв различных областей таб-панели
        UIManager.put ( "TabbedPane.contentBorderInsets", new Insets ( 1, 2, 1, 2 ) );
        UIManager.put ( "TabbedPane.tabAreaInsets", new Insets ( 0, 2, 0, 2 ) );
        UIManager.put ( "TabbedPane.tabInsets", new Insets ( 1, 4, 2, 4 ) );
        UIManager.put ( "TabbedPane.selectedTabPadInsets", new Insets ( 2, 2, 2, 1 ) );

        // Рендерер списка
        UIManager.put ( "List.cellRenderer", new UIDefaults.ActiveValue ()
        {
            public Object createValue ( UIDefaults table )
            {
                return new WebListCellRenderer.UIResource ();
            }
        } );

        // Иконки для OptionPane
        UIManager.put ( "OptionPane.informationIcon", WebOptionPaneUI.INFORMATION_ICON );
        UIManager.put ( "OptionPane.questionIcon", WebOptionPaneUI.QUESTION_ICON );
        UIManager.put ( "OptionPane.warningIcon", WebOptionPaneUI.WARNING_ICON );
        UIManager.put ( "OptionPane.errorIcon", WebOptionPaneUI.ERROR_ICON );

        // Перевод WebFileChooser'а
        UIManager.put ( "WebFileChooser.title", "Choose files" );
        UIManager.put ( "WebFileChooser.back", "Back" );
        UIManager.put ( "WebFileChooser.forward", "Forward" );
        UIManager.put ( "WebFileChooser.path", "Placement:" );
        UIManager.put ( "WebFileChooser.folderup", "Folder up" );
        UIManager.put ( "WebFileChooser.home", "Home directory" );
        UIManager.put ( "WebFileChooser.newfolder", "Create new folder" );
        UIManager.put ( "WebFileChooser.newfolder.name", "New folder" );
        UIManager.put ( "WebFileChooser.refresh", "Reload folder content" );
        UIManager.put ( "WebFileChooser.delete", "Delete file or folder" );
        UIManager.put ( "WebFileChooser.view", "Change view" );
        UIManager.put ( "WebFileChooser.view.table", "Details" );
        UIManager.put ( "WebFileChooser.view.icons", "Icons" );
        UIManager.put ( "WebFileChooser.view.tiles", "Tiles" );
        UIManager.put ( "WebFileChooser.files.selected", "Selected files:" );
        UIManager.put ( "WebFileChooser.files.format", "Filter:" );
        UIManager.put ( "WebFileChooser.choose", "Choose" );
        UIManager.put ( "WebFileChooser.cancel", "Cancel" );

        // Перевод WebColorChooser'а
        UIManager.put ( "WebColorChooser.title", "Choose color" );
        UIManager.put ( "WebColorChooser.webonly", "Web only" );
        UIManager.put ( "WebColorChooser.webonly.mnemonic", 'W' );
        UIManager.put ( "WebColorChooser.choose", "Choose" );
        UIManager.put ( "WebColorChooser.choose.mnemonic", 'C' );
        UIManager.put ( "WebColorChooser.reset", "Reset" );
        UIManager.put ( "WebColorChooser.reset.mnemonic", 'R' );
        UIManager.put ( "WebColorChooser.cancel", "Cancel" );
        UIManager.put ( "WebColorChooser.cancel.mnemonic", 'l' );

        // Перевод фильтров
        UIManager.put ( "WebFileChooser.filter.allfiles", "All files" );
        UIManager.put ( "WebFileChooser.filter.directoriesonly", "Folders only" );
        UIManager.put ( "WebFileChooser.filter.filesonly", "Files only" );
        UIManager.put ( "WebFileChooser.filter.imagesonly", "Images only" );
        UIManager.put ( "WebFileChooser.filter.nonhiddenonly", "Non-hidden files only" );

        // Перевод названий размеров файлов
        UIManager.put ( "FileUtils.file.size.b", "b" );
        UIManager.put ( "FileUtils.file.size.kb", "Kb" );
        UIManager.put ( "FileUtils.file.size.mb", "Mb" );
        UIManager.put ( "FileUtils.file.size.gb", "Gb" );
        UIManager.put ( "FileUtils.file.size.tb", "Tb" );
        UIManager.put ( "FileUtils.file.size.pb", "Pb" );

        // Перевод кнопок WebOptionPane
        UIManager.put ( "OptionPane.okButtonText", "Ok" );
        UIManager.put ( "OptionPane.okButtonMnemonic", "O" );
        UIManager.put ( "OptionPane.yesButtonText", "Yes" );
        UIManager.put ( "OptionPane.yesButtonMnemonic", "Y" );
        UIManager.put ( "OptionPane.noButtonText", "No" );
        UIManager.put ( "OptionPane.noButtonMnemonic", "N" );
        UIManager.put ( "OptionPane.cancelButtonText", "Cancel" );
        UIManager.put ( "OptionPane.cancelButtonMnemonic", "C" );

        // Инициализируем менеджеры
        HotkeyManager.initializeManager ();
        FocusManager.initializeManager ();
    }
}
