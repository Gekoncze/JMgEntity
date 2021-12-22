package cz.mg.entity.explorer.gui.components.views;


public class ListView { // TODO
//    if(content instanceof EntityMultiSelectContent){
//        this.buttons.addCollectionFirst(new List<>(
//            new UiButton(ImageGallery.UP, null, "Move up", this::onMoveRowUp),
//            new UiButton(ImageGallery.DOWN, null, "Move down", this::onMoveRowDown),
//            new UiButton(ImageGallery.CREATE_ROW, null, "Add row", this::onAddRow),
//            new UiButton(ImageGallery.DELETE_ROW, null, "Remove row", this::onRemoveRow)
//        ));
//    }

//    if(content instanceof EntityMultiSelectContent){
//        this.buttons.addCollectionFirst(new List<>(
//            new UiButton(ImageGallery.UP, null, "Move up", this::onMoveRowUp),
//            new UiButton(ImageGallery.DOWN, null, "Move down", this::onMoveRowDown),
//            new UiButton(ImageGallery.CREATE_ROW, null, "Add row", this::onAddRow),
//            new UiButton(ImageGallery.DELETE_ROW, null, "Remove row", this::onRemoveRow)
//        ));
//    }

//    if(content instanceof EntityMultiSelectContent){
//        this.buttons.addCollectionFirst(new List<>(
//            new UiButton(ImageGallery.UP, null, "Move up", this::onMoveRowUp),
//            new UiButton(ImageGallery.DOWN, null, "Move down", this::onMoveRowDown),
//            new UiButton(ImageGallery.CREATE_ROW, null, "Add row", this::onAddRow),
//            new UiButton(ImageGallery.DELETE_ROW, null, "Remove row", this::onRemoveRow)
//        ));
//    }

//    private void onMoveRowUp() {
//        ((EntityMultiSelectContent)content).moveRowUp();
//    }
//
//    private void onMoveRowDown() {
//        ((EntityMultiSelectContent)content).moveRowDown();
//    }
//
//    private void onAddRow() {
//        ((EntityMultiSelectContent)content).addRow();
//    }
//
//    private void onRemoveRow() {
//        ((EntityMultiSelectContent)content).removeRow();
//    }

//    private void onMoveRowUp() {
//        ((EntityMultiSelectContent)content).moveRowUp();
//    }
//
//    private void onMoveRowDown() {
//        ((EntityMultiSelectContent)content).moveRowDown();
//    }
//
//    private void onAddRow() {
//        ((EntityMultiSelectContent)content).addRow();
//    }
//
//    private void onRemoveRow() {
//        if(onDeleteButtonClicked()){
//            ((EntityMultiSelectContent)content).removeRow();
//        }
//    }
    
//    private void onMoveRowUp() {
//        ((EntityMultiSelectContent)content).moveRowUp();
//    }
//
//    private void onMoveRowDown() {
//        ((EntityMultiSelectContent)content).moveRowDown();
//    }
//
//    private void onAddRow() {
//        ((EntityMultiSelectContent)content).addRow();
//    }
//
//    private void onRemoveRow() {
//        ((EntityMultiSelectContent)content).removeRow();
//    }
}




//package cz.mg.nativeapplication.gui.components.entity.content;
//
//import cz.mg.annotations.classes.Utility;
//import cz.mg.annotations.requirement.Mandatory;
//import cz.mg.annotations.requirement.Optional;
//import cz.mg.annotations.storage.Link;
//import cz.mg.annotations.storage.Part;
//import cz.mg.annotations.storage.Shared;
//import cz.mg.collections.list.List;
//import cz.mg.entity.EntityField;
//import cz.mg.nativeapplication.gui.services.ApplicationProvider;
//import cz.mg.nativeapplication.gui.services.ObjectImageProvider;
//import cz.mg.nativeapplication.gui.ui.controls.field.UiField;
//import cz.mg.nativeapplication.gui.ui.controls.field.UiListField;
//import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseFactory;
//import cz.mg.nativeapplication.gui.ui.controls.field.other.UiFieldBaseWrapper;
//import cz.mg.nativeapplication.explorer.history.actions.AddListItemAction;
//import cz.mg.nativeapplication.explorer.history.actions.RemoveListItemAction;
//import cz.mg.nativeapplication.explorer.history.actions.SetListItemAction;
//import cz.mg.nativeapplication.mg.services.other.CollectionTypeProvider;
//
//import java.util.Iterator;
//import java.util.Objects;
//
//
//public @Utility class EntityMultiSelectContent extends EntitySelectContent { TODO
//    private final @Mandatory @Shared CollectionTypeProvider collectionTypeProvider = new CollectionTypeProvider();
//    private final @Mandatory @Shared ObjectImageProvider objectImageProvider = new ObjectImageProvider();
//    private final @Mandatory @Shared ApplicationProvider applicationProvider = new ApplicationProvider();
//
//    private final @Mandatory @Link Object entity;
//    private final @Mandatory @Link EntityField entityField;
//    private final @Mandatory @Link List list;
//    private final @Mandatory @Part UiListField field;
//
//    public EntityMultiSelectContent(
//        @Mandatory Object entity,
//        @Mandatory EntityField entityField,
//        @Mandatory UiFieldBaseFactory fieldBaseFactory
//    ) {
//        this.entity = entity;
//        this.entityField = entityField;
//        this.list = (List) Objects.requireNonNull(entityField.get(entity)); // TODO - shall we allow null ???
//        this.field = new UiListField(objectImageProvider::getOptional, fieldBaseFactory);
//    }
//
//    @Override
//    public @Mandatory Object getParent() {
//        return list;
//    }
//
//    @Override
//    public @Optional Integer getChildIndex() {
//        return field.getSelectedIndex();
//    }
//
//    @Override
//    public @Mandatory String getName() {
//        return entityField.getName();
//    }
//
//    @Override
//    public @Mandatory Class getType() {
//        return collectionTypeProvider.get(entityField.getField());
//    }
//
//    @Override
//    public @Optional UiFieldBaseWrapper getFieldBase() {
//        Integer i = field.getSelectedIndex();
//        if(i != null){
//            return field.getFields().get(i);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public @Mandatory UiField getField() {
//        return field;
//    }
//
//    @Override
//    public @Optional Object getValue(){
//        Integer i = field.getSelectedIndex();
//        if(i != null){
//            return getValueAt(i);
//        } else {
//            return null;
//        }
//    }
//
//    private @Optional Object getValueAt(int i){
//        return list.get(i);
//    }
//
//    @Override
//    public void setValue(@Optional Object value){
//        Integer i = field.getSelectedIndex();
//        if(i != null){
//            setValueAt(i, value);
//        }
//    }
//
//    private void setValueAt(int i, @Optional Object value){
//        if(i >= 0 && i < list.count()){
//            applicationProvider.get().getExplorer().getTransactionManager().run( // TODO - replace with node set call
//                new SetListItemAction(list, i, getValueAt(i), value)
//            );
//            refresh();
//        }
//    }
//
//    private void addRowAt(int i){
//        if(i >= 0 && i <= list.count()){
//            applicationProvider.get().getExplorer().getTransactionManager().run( // TODO - replace with node set call
//                new AddListItemAction(list, i, null)
//            );
//            refresh();
//        }
//    }
//
//    public void addRow(){
//        Integer i = field.getSelectedIndex();
//        if(i != null){
//            addRowAt(i);
//        } else {
//            addRowAt(list.count());
//        }
//    }
//
//    private void removeRowAt(int i){
//        if(i >= 0 && i < list.count()){
//            applicationProvider.get().getExplorer().getTransactionManager().run( // TODO - replace with node set call
//                new RemoveListItemAction(list, i, list.get(i))
//            );
//            refresh();
//        }
//    }
//
//    public void removeRow(){
//        Integer i = field.getSelectedIndex();
//        if(i != null){
//            removeRowAt(i);
//        }
//    }
//
//    private void moveRow(int srcIndex, int dstIndex){
//        if(srcIndex != dstIndex){
//            if(srcIndex >= 0 && srcIndex < list.count()){
//                if(dstIndex >= 0 && dstIndex < list.count()){
//                    Object value = getValueAt(srcIndex);
//                    applicationProvider.get().getExplorer().getTransactionManager().run( // TODO - replace with node set call
//                        new RemoveListItemAction(list, srcIndex, value)
//                    );
//                    applicationProvider.get().getExplorer().getTransactionManager().run( // TODO - replace with node set call
//                        new AddListItemAction(list, dstIndex, value)
//                    );
//                    refresh();
//                }
//            }
//        }
//    }
//
//    public void moveRowUp(){
//        Integer i = field.getSelectedIndex();
//        if(i != null){
//            moveRow(i, i - 1);
//            field.setSelectedIndex(i - 1);
//        }
//    }
//
//    public void moveRowDown(){
//        Integer i = field.getSelectedIndex();
//        if(i != null){
//            moveRow(i, i + 1);
//            field.setSelectedIndex(i + 1);
//        }
//    }
//
//    @Override
//    public void refresh() {
//        field.setValues(list);
//    }
//
//    @Override
//    public void softRefresh() {
//        if(field.getFields().count() == list.count()){
//            Iterator iterator = list.iterator();
//            for(UiFieldBaseWrapper field : field.getFields()){
//                field.setValue(iterator.next());
//                field.lock();
//            }
//        } else {
//            refresh();
//        }
//    }
//}
