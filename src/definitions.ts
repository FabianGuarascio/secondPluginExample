export interface ExamplePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  echo2():void;
  addListener(
    eventName: 'pushNotificationReceived',
    listenerFunc: (notification: any) => void,
  ): Promise<any>;
  getStoredNotifications():Promise<any>;
}
