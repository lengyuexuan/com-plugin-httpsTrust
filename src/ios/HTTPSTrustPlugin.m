/********* HTTPSTrustPlugin.m Cordova Plugin Implementation *******/

#import <Cordova/CDV.h>

@interface HTTPSTrustPlugin : CDVPlugin {
  // Member variables go here.
}

- (void)ignoreCerts:(CDVInvokedUrlCommand*)command;
@end

@implementation HTTPSTrustPlugin

- (void)ignoreCerts:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:echo];
   [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end

@implementation NSURLRequest(DataController) 
+ (BOOL)allowsAnyHTTPSCertificateForHost:(NSString *)host 
{ 
return YES; 
} 
@end