package org.insia.mdmsCorp.website.as3
{
    import flash.display.Sprite;
    import flash.events.Event;
    import flash.events.ProgressEvent;
    import flash.utils.Timer;
    import flash.events.TimerEvent;
    import mx.events.*;
    import mx.preloaders.Preloader;
    import mx.preloaders.DownloadProgressBar;
    import org.insia.mdmsCorp.website.as3.WelcomeScreen;
    import mx.core.Application;
    import mx.controls.Alert;
    import flash.filters.BlurFilter;
    import flash.filters.BitmapFilterQuality;

    public class CustomPreloader extends DownloadProgressBar 
    {
        public var wcs:WelcomeScreen;
        
        public function CustomPreloader() 
        {
            super();
            wcs = new WelcomeScreen();
            this.addChild(wcs)
        }
    
        override public function set preloader( preloader:Sprite ):void 
        {                   
            preloader.addEventListener( FlexEvent.INIT_COMPLETE , flexInitComplete );
            preloader.addEventListener( FlexEvent.PRELOADER_DONE , creationComplete );
        }
    
        private function creationComplete(event:Event):void
        {
			wcs.closeScreen();
        }
    
        private function flexInitComplete( event:Event ):void 
        {      
            wcs.ready = true;
            dispatchEvent( new Event( Event.COMPLETE ) );
         	var application:Application = Application(Application.application);
        	if (application.filters.length == 0)
        	{
	        	application.filters = [new BlurFilter(15, 15, BitmapFilterQuality.HIGH)];
        	} 
        }
    }
}