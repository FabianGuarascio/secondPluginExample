# second-custom-plugin

no description

## Install

```bash
npm install second-custom-plugin
npx cap sync
```

## API

<docgen-index>

* [`echo(...)`](#echo)
* [`echo2()`](#echo2)
* [`addListener('pushNotificationReceived', ...)`](#addlistenerpushnotificationreceived-)
* [`getStoredNotifications()`](#getstorednotifications)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### echo(...)

```typescript
echo(options: { value: string; }) => Promise<{ value: string; }>
```

| Param         | Type                            |
| ------------- | ------------------------------- |
| **`options`** | <code>{ value: string; }</code> |

**Returns:** <code>Promise&lt;{ value: string; }&gt;</code>

--------------------


### echo2()

```typescript
echo2() => void
```

--------------------


### addListener('pushNotificationReceived', ...)

```typescript
addListener(eventName: 'pushNotificationReceived', listenerFunc: (notification: any) => void) => Promise<any>
```

| Param              | Type                                        |
| ------------------ | ------------------------------------------- |
| **`eventName`**    | <code>'pushNotificationReceived'</code>     |
| **`listenerFunc`** | <code>(notification: any) =&gt; void</code> |

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------


### getStoredNotifications()

```typescript
getStoredNotifications() => Promise<any>
```

**Returns:** <code>Promise&lt;any&gt;</code>

--------------------

</docgen-api>
