package org.insia.mdmsCorp.website.as3
{
    import flash.display.Loader;
    import flash.utils.ByteArray;
    import flash.events.MouseEvent;
    import flash.events.TimerEvent;
    import flash.utils.Timer;
    import mx.core.Application;
    import flash.filters.BlurFilter;
    import flash.filters.BitmapFilterQuality;
    import flash.display.Sprite;
    import mx.controls.Alert;
    import mx.effects.Blur;
    
    public class WelcomeScreen extends Loader
    {
        [ Embed(source="../ressources/swf/welcome.swf", mimeType="application/octet-stream") ]
        public var WelcomeGif:Class;
        public var timer:Timer;
       // private var blurIntensity:Number = 10;
        public var ready:Boolean = false; 
        
        public function WelcomeScreen()
        {	
            this.visible = false;
            this.alpha = 0;
            timer = new Timer( 1 );
            timer.addEventListener( TimerEvent.TIMER, updateView );
            timer.start();
            this.loadBytes( new WelcomeGif() as ByteArray );
        }
        
        public function updateView( event:TimerEvent ):void
        {
            this.alpha = 1;
            this.stage.addChild(this)
            this.x = this.stage.stageWidth/2 - this.width/2
            this.y = this.stage.stageHeight/2 - this.height/2
            this.visible=true;
        }
        
        public function closeScreen():void
        {
            timer.removeEventListener( TimerEvent.TIMER, updateView );
            timer.addEventListener( TimerEvent.TIMER, closeScreenFade);
        }
        
        public function closeScreenFade( event:TimerEvent ):void
        {
         	var application:Application = Application(Application.application);
            application.filters = null;
            timer.stop()
            this.parent.removeChild(this);
            this.alpha = 100;
        }                 
    }
}