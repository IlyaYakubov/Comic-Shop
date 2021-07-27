Скачать и запустить приложение можно по ссылке: https://drive.google.com/file/d/13ERNhWTWAzM6iaqCsVBpdpGsmbuZ34ZJ/view?usp=sharing

0. Скачать библиотеку Java FX (https://gluonhq.com/products/javafx/)
   <hr>
1. Копировать путь в буфер обмена:
   <путь до JavaFX>\javafx-sdk-<версия>\lib
   <hr>
2. В настройках проекта - Libraries - Add Java - вставить путь из буфера обмена
   <hr>
3. В Settings среды разработки idea в меню Appearance && Behavior - Path Variables добавить PATH_TO_FX, а значение путь из буфера обмена
   <hr>
4. Добавить в конфигурацию запуска - в Modify options - Enable VM option:
   --module-path ${PATH_TO_FX} --add-modules=javafx.controls,javafx.fxml --add-modules javafx.controls,javafx.fxml --add-exports javafx.graphics/com.sun.javafx.sg.prism=ALL-UNNAMED
