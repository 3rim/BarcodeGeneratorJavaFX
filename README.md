# BarcodeGeneratorJavaFX
I created this application for my current work (work in progress :smile: ). We had to add barcodes to over 1000 iPads to inventory them. <br>
Currently it only supports two layouts and either text to text or text to Barcode128. 
<ul>
  <li>JavaFX 19 </li>
  <li>iTextpdf </li>
  <li>opencsv </li>
</ul>

<div align="center">

![image](https://user-images.githubusercontent.com/37411005/225304435-c7d973a9-2850-4eec-a0c4-3d6ab3e328ca.png)

![image](https://user-images.githubusercontent.com/37411005/225304560-7c048446-c0a7-44cb-b65d-7fa39d995223.png)

![image](https://user-images.githubusercontent.com/37411005/225310804-88a3bf6a-b80e-4304-b2ad-9f8a5f40b246.png)

Lyreco A4(left) , Avery A4(right)
<br>
<img src="https://user-images.githubusercontent.com/37411005/225311349-d2808310-8cdb-44d7-9ff6-d94097879ef2.png" width="400" height="500">
<img src="https://user-images.githubusercontent.com/37411005/225311074-ff51fbbd-e543-441a-8c04-0c18507708f6.png" width="400" height="500">
</div>

## Usage
Attention: I used a custom created javaFX-UI component <a href="https://github.com/3rim/CustomJavaFXComponents"> ToggleSwitch </a>. <br>
You will need to install it to your local .m2 repo otherwise it wont work.

## installation
<a href="https://github.com/3rim/CustomJavaFXComponents"> Create executable jar file for JavaFX 11+ using Maven [2022] </a>. <br>
```
mvn clean install
```


