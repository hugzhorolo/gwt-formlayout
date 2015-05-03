# gwt-formlayout
Customizable form layout with json data binding

![Demo form](https://raw.githubusercontent.com/hugzhorolo/gwt-formlayout/master/doc/DemoForm.png)

## Demo code
```java
JSONObject sampleData = new JSONObject();
sampleData.put("Field 1", new JSONNumber(1.0));
sampleData.put("Field 2", new JSONString("Hello world"));
sampleData.put("Field 3", JSONBoolean.getInstance(true));

// Sample 1 - basic usage
final FormLayout formLayout = new FormLayout(sampleData.toString());
RootPanel.get().add(formLayout);

Button button = new Button("Apply");
button.addStyleName(style.apply());
formLayout.appendWidgetToBottom(button);
```

