package org.insia.mdmsCorp.website.as3 {
    import flash.events.*;
    import flash.net.FileFilter;
    import flash.net.FileReference;
    import flash.net.URLRequest;

    import mx.controls.Alert;
    import mx.core.Application;
    import mx.managers.PopUpManager;

    import org.insia.mdmsCorp.website.flex.ProgressBarDialogBox;

    public class FileReferenceUpload  {
        private var uploadURL:URLRequest;
        private var file:FileReference;
        [Bindable]
        private var progressBarDialogBox:ProgressBarDialogBox;
        [Bindable]
        private var textInput:String;


        public function FileReferenceUpload(fileFilter:FileFilter, textInput:String) {
            uploadURL = new URLRequest();
            uploadURL.url = "http://localhost:8080/uploadFile/Upload";
            file = new FileReference();
            configureListeners(file);
            file.browse( new Array(fileFilter));
            this.textInput = textInput;
        }

        private function configureListeners(dispatcher:IEventDispatcher):void {
            dispatcher.addEventListener(Event.CANCEL, cancelHandler);
            dispatcher.addEventListener(Event.COMPLETE, completeHandler);
            dispatcher.addEventListener(HTTPStatusEvent.HTTP_STATUS, httpStatusHandler);
            dispatcher.addEventListener(IOErrorEvent.IO_ERROR, ioErrorHandler);
            dispatcher.addEventListener(Event.OPEN, openHandler);
            dispatcher.addEventListener(ProgressEvent.PROGRESS, progressHandler);
            dispatcher.addEventListener(SecurityErrorEvent.SECURITY_ERROR, securityErrorHandler);
            dispatcher.addEventListener(Event.SELECT, selectHandler);
        }

        private function cancelHandler(event:Event):void {
            trace("cancelHandler: " + event);
        }

        private function completeHandler(event:Event):void {
        	var file:FileReference = FileReference(event.target);
        	PopUpManager.removePopUp(progressBarDialogBox);

        	if (textInput == "bookCanvas")
        	{
        		Application.application.booksCanvas.bookURLCover.text = file.name;
        	}
        	if (textInput == "eBookCanvas1")
        	{
        		Application.application.eBooksCanvas.eBookURLCover.text = file.name;
        	}
        	if (textInput == "eBookCanvas2")
        	{
        		Application.application.eBooksCanvas.eBookURL.text = file.name;
        	}
        }

        private function httpStatusHandler(event:HTTPStatusEvent):void {
            trace("httpStatusHandler: " + event);
        }

        private function ioErrorHandler(event:IOErrorEvent):void {
             Alert.show("Upload failed " + event);
        }

        private function openHandler(event:Event):void {
            trace("openHandler: " + event);
        }

        private function progressHandler(event:ProgressEvent):void
        {
            var file:FileReference = FileReference(event.target);
			progressBarDialogBox.progressBar.setProgress(event.bytesLoaded / event.bytesTotal * 100 , 100);
			progressBarDialogBox.progressBar.label= Math.floor(event.bytesLoaded / event.bytesTotal * 100) + "%";
        }

        private function securityErrorHandler(event:SecurityErrorEvent):void {
            trace("securityErrorHandler: " + event);
        }

        private function selectHandler(event:Event):void {
            var file:FileReference = FileReference(event.target);
            trace("selectHandler: name=" + file.name + " URL=" + uploadURL.url);
            file.upload(uploadURL);
			progressBarDialogBox = ProgressBarDialogBox(PopUpManager.createPopUp(Application.application.backOfficeViewStack,ProgressBarDialogBox,true));
			PopUpManager.centerPopUp(progressBarDialogBox);
        }
    }
}