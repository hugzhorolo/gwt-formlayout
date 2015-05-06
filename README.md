# gwt-formlayout
Customizable, responsive form layout with json data binding

## Example
![Form example](https://raw.githubusercontent.com/hugzhorolo/gwt-formlayout/master/doc/DemoForm.png)
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

## Features
* basic widgets with pretty design: textbox, listbox, checkbox
* implemented with responsivity in mind
* easily replacable fields
* easily replacable layout

[Donate!](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MPTFKRPF7LUHL)
