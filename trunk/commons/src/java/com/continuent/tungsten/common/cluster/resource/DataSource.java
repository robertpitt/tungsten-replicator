
package com.continuent.tungsten.common.cluster.resource;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import com.continuent.tungsten.common.config.TungstenProperties;
import com.continuent.tungsten.common.patterns.order.HighWaterResource;
import com.continuent.tungsten.common.patterns.order.Sequence;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataSource extends Resource implements Serializable
{
    private static final long       serialVersionUID               = 8153881753668230575L;
    private static final Logger     logger                         = Logger.getLogger(DataSource.class);

    public static final String      NAME                           = "name";
    public static final String      CLUSTERNAME                    = "dataServiceName";
    public static final String      PRECEDENCE                     = "precedence";
    public static final String      ISAVAILABLE                    = "isAvailable";
    public static final String      STATE                          = "state";
    public static final String      ISCOMPOSITE                    = "isComposite";
    public static final String      ALERT_STATUS                   = "alertStatus";
    public static final String      ALERT_MESSAGE                  = "alertMessage";
    public static final String      ALERT_TIME                     = "alertTime";
    public static final String      APPLIED_LATENCY                = "appliedLatency";
    public static final String      HOST                           = "host";
    public static final String      ROLE                           = "role";
    public static final String      VENDOR                         = "vendor";
    public static final String      DRIVER                         = "driver";
    public static final String      URL                            = "url";
    public static final String      LASTERROR                      = "lastError";
    public static final String      LASTSHUNREASON                 = "lastShunReason";
    public static final String      HIGHWATER                      = "highWater";
    public static final String      VIPINTERFACE                   = "vipInterface";
    public static final String      VIPADDRESS                     = "vipAddress";
    public static final String      VIPISBOUND                     = "vipIsBound";
    public static final String      ACTIVE_CONNECTION_COUNT        = "activeConnectionsCount";
    public static final String      CONNECTIONS_CREATED_COUNT      = "connectionsCreatedCount";
    public static final String      TYPE                           = "type";

    // Defaults
    public static final double      DEFAULT_APPLIED_LATENCY        = 0.0;

    /**
     * The following six properties are the absolute minimum that are required
     * in order to derive a datasource that will work within the rest of the
     * framework. In particular, if 'host' is missing - it refers to the host
     * where the datasource is resident - the framework that associates
     * replicators with datasources will fail to work.
     */
    private String                  dataServiceName                = "";
    private String                  host                           = "";
    private DataSourceRole          role                           = DataSourceRole.undefined;
    private String                  vendor                         = "";
    private String                  driver                         = "";
    private String                  url                            = "";
    private boolean                 isComposite                    = false;
    private int                     precedence                     = 0;
    private boolean                 available                      = false;

    private ResourceState           state                          = ResourceState.UNKNOWN;

    private DataSourceAlertStatus   alertStatus                    = DataSourceAlertStatus.UNKNOWN;
    private String                  alertMessage                   = "";
    private long                    alertTime                      = System.currentTimeMillis();

    private String                  lastError                      = "";
    private String                  lastShunReason                 = "";

    private double                  appliedLatency                 = DEFAULT_APPLIED_LATENCY;
    private Date                    updateTimestamp                = new Date();
    private Date                    lastUpdate                     = new Date();

    @SuppressWarnings("unused")
    private boolean                 isStandby                      = false;

    private HighWaterResource       highWater                      = new HighWaterResource();

    // Statistics.
    private AtomicLong              activeConnectionsCount         = new AtomicLong(
                                                                           0);
    private AtomicLong              connectionsCreatedCount        = new AtomicLong(
                                                                           0);
    private AtomicLong              statementsCreatedCount         = new AtomicLong(
                                                                           0);
    private AtomicLong              preparedStatementsCreatedCount = new AtomicLong(
                                                                           0);
    private AtomicLong              callableStatementsCreatedCount = new AtomicLong(
                                                                           0);

    /**
     * Represents a single life cycle transition for this datasource. A
     * transition occurs for any update() in which
     */
    private Sequence                sequence                       = new Sequence();

    private AtomicInteger           enabled                        = new AtomicInteger(
                                                                           0);
    /**
     * VIP management properties
     */
    private String                  vipInterface                   = "";
    private String                  vipAddress                     = "";
    private boolean                 vipIsBound                     = false;

    /** Retains all non-closed connections to this data source */
    private Set<DatabaseConnection> activeConnections              = Collections
                                                                           .synchronizedSet(new HashSet<DatabaseConnection>());

    public static void main(String[] argv)
    {
        ObjectMapper mapper = new ObjectMapper();

        try
        {
            String dsString = new String("{\"name\":\"m2.continuent.com\",\"dataServiceName\":\"sj\",\"host\":\"m2.continuent.com\",\"type\":\"DATASOURCE\",\"childType\":\"UNDEFINED\",\"role\":\"master\",\"vendor\":\"mysql\",\"driver\":\"com.mysql.jdbc.Driver\",\"url\":\"jdbc:mysql://m2.continuent.com:3306/${DBNAME}?jdbcCompliantTruncation=false&zeroDateTimeBehavior=convertToNull&tinyInt1isBit=false&allowMultiQueries=true&yearIsDateType=false\",\"precedence\":99,\"available\":false,\"state\":\"SHUNNED\",\"alertStatus\":\"SHUNNED\",\"alertMessage\":\"\",\"alertTime\":1354638477444,\"lastError\":\"--\",\"lastShunReason\":\"FAILED-OVER-TO-m1.continuent.com\",\"appliedLatency\":1.184,\"updateTimestamp\":1354641957936,\"lastUpdate\":1354641957936,\"activeConnectionsCount\":0,\"enabled\":0,\"vipInterface\":\"\",\"vipAddress\":\"\",\"vipIsBound\":false,\"composite\":false,\"dataSourceRole\":\"master\",\"isAvailable\":false,\"standby\":false,\"connectionsCreated\":0,\"statementsCreated\":0,\"preparedStatementsCreated\":0,\"callableStatementsCreated\":0,\"master\":true,\"slave\":false,\"relay\":false,\"key\":\"m2.continuent.com\",\"executable\":false,\"container\":false}");
            DataSource ds = mapper.readValue(dsString, DataSource.class);
            System.out.println(ds);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    /**
     * Creates a new <code>DataSource</code> object
     */
    public DataSource()
    {
        super(ResourceType.DATASOURCE, "unknown");
    }

    public DataSource(TungstenProperties props)
    {
        super(ResourceType.DATASOURCE, props.getString(DataSource.NAME,
                "unknown", true));
        props.applyProperties(this, true);
        String state = props.getString(DataSource.STATE);
        /*
         * Backwards compatibility - previous versions don't have state.
         */
        if (state == null)
        {
            if (props.getBoolean(DataSource.ISAVAILABLE) == true)
                setState(ResourceState.ONLINE);
            else
                setState(ResourceState.OFFLINE);
        }
        else
        {
            setState(ResourceState.valueOf(state));
        }

        // Backwards compatible.
        String alertStatus = props.getString(DataSource.ALERT_STATUS);

        if (alertStatus == null)
        {
            switch (getState())
            {
                case ONLINE :
                    setAlert(DataSourceAlertStatus.OK, "");
                    break;
                case OFFLINE :
                case FAILED :
                    setAlert(DataSourceAlertStatus.WARN, "alert reason unknown");
                    break;
                case SHUNNED :
                    setAlert(DataSourceAlertStatus.SHUNNED, "");
                    break;

                default :
                    setAlert(DataSourceAlertStatus.UNKNOWN, "");
                    break;
            }
        }
        else
        {
            String alertMessage = props.getString(DataSource.ALERT_MESSAGE, "",
                    false);
            setAlertStatus(DataSourceAlertStatus.valueOf(alertStatus));
            setAlertMessage(alertMessage);
            setAlertTime(props.getLong(DataSource.ALERT_TIME));
        }
    }

    @JsonCreator
    public DataSource(@JsonProperty("name") String key,
            @JsonProperty("dataServiceName") String clusterName,
            @JsonProperty("host") String host)
    {
        super(ResourceType.DATASOURCE, key);
        this.dataServiceName = clusterName;
        this.host = host;
    }

    public void addConnection(DatabaseConnection conn)
    {
        // thread-safe: activeConnections is a synchronizedSet
        activeConnections.add(conn);
    }

    public void removeConnection(DatabaseConnection conn)
    {
        // thread-safe: activeConnections is a synchronizedSet
        activeConnections.remove(conn);
    }

    public int getActiveConnectionsCount()
    {
        return activeConnections.size();
    }

    /**
     * Provides a reference to the synchronized set of active connections. Any
     * iterator operation on this set MUST be synchronized on the set object
     * 
     * @return the active connections set
     */
    @JsonIgnore
    public Set<DatabaseConnection> getActiveConnections()
    {
        return activeConnections;
    }

    static public TungstenProperties updateFromReplicatorStatus(
            TungstenProperties replicatorProps, TungstenProperties dsProps)
    {
        dsProps.setString(NAME, replicatorProps.getString(Replicator.SOURCEID));
        dsProps.setString(CLUSTERNAME,
                replicatorProps.getString(Replicator.CLUSTERNAME));
        dsProps.setString(HOST,
                replicatorProps.getString(Replicator.DATASERVERHOST));

        dsProps.setString(VENDOR,
                replicatorProps.getString(Replicator.RESOURCE_VENDOR));
        dsProps.setString(URL,
                replicatorProps.getString(Replicator.RESOURCE_JDBC_URL));
        dsProps.setString(DRIVER,
                replicatorProps.getString(Replicator.RESOURCE_JDBC_DRIVER));
        dsProps.setString(ROLE, replicatorProps.getString(Replicator.ROLE)
                .toLowerCase());
        dsProps.setString(HIGHWATER, String.format("%d(%s)",
                replicatorProps.getLong(Replicator.LATEST_EPOCH_NUMBER),
                replicatorProps.getString(Replicator.APPLIED_LAST_EVENT_ID)));
        dsProps.setDouble(APPLIED_LATENCY,
                replicatorProps.getDouble(Replicator.APPLIED_LATENCY));
        dsProps.setString(VIPINTERFACE, replicatorProps.getString(
                Replicator.RESOURCE_VIP_INTERFACE, null, true));
        dsProps.setString(VIPADDRESS, replicatorProps.getString(
                Replicator.RESOURCE_VIP_ADDRESS, null, true));

        return dsProps;
    }

    static public TungstenProperties createFromReplicatorStatus(
            TungstenProperties replicatorProps)
    {
        DataSource newDs = new DataSource(
                replicatorProps.getString(Replicator.SOURCEID),
                replicatorProps.getString(Replicator.CLUSTERNAME),
                replicatorProps.getString(Replicator.SOURCEID));

        newDs.setVendor(replicatorProps.getString(Replicator.RESOURCE_VENDOR));
        newDs.setUrl(replicatorProps.getString(Replicator.RESOURCE_JDBC_URL)
                .trim());
        newDs.setDriver(replicatorProps.getString(
                Replicator.RESOURCE_JDBC_DRIVER).trim());
        newDs.setRole(replicatorProps.getString(Replicator.ROLE).toLowerCase());

        boolean isStandby = replicatorProps
                .getBoolean(Replicator.RESOURCE_IS_STANDBY_DATASOURCE);

        if (isStandby)
        {
            // Standby data sources are not available for reads or writes
            newDs.setIsAvailable(false);
            newDs.setState(ResourceState.OFFLINE);
            newDs.setStandby(true);
        }
        else
        {
            newDs.setIsAvailable(true);
            newDs.setState(ResourceState.ONLINE);
            newDs.setStandby(false);
        }

        newDs.setPrecedence(99);

        newDs.setHighWater(replicatorProps.getLong(
                Replicator.LATEST_EPOCH_NUMBER, "0", false), replicatorProps
                .getString(Replicator.APPLIED_LAST_EVENT_ID));
        newDs.setAppliedLatency(replicatorProps
                .getDouble(Replicator.APPLIED_LATENCY));

        newDs.setVipInterface(replicatorProps.getString(
                Replicator.RESOURCE_VIP_INTERFACE, null, true));

        newDs.setVipAddress(replicatorProps.getString(
                Replicator.RESOURCE_VIP_ADDRESS, null, true));

        newDs.setVipIsBound(false);

        newDs.setComposite(false);

        return newDs.toProperties();
    }

    public String getDriver()
    {
        if (driver == null)
            return "";

        return driver;
    }

    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public String getUrl()
    {
        if (url == null)
            return "";

        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getRole()
    {
        return role.toString();
    }

    public DataSourceRole getDataSourceRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = DataSourceRole.valueOf(role.toLowerCase());
    }

    public void setDataSourceRole(DataSourceRole role)
    {
        this.role = role;
    }

    public int getPrecedence()
    {
        return precedence;
    }

    public void setPrecedence(int precedence)
    {
        this.precedence = precedence;
    }

    public String getVendor()
    {
        if (vendor == null)
            return "";

        return vendor;
    }

    public void setVendor(String vendor)
    {
        this.vendor = vendor;
    }

    /**
     * @return the isAvailable
     */
    public boolean isAvailable()
    {
        return available;
    }

    /**
     * @return the isAvailable
     */
    public boolean getIsAvailable()
    {
        return available;
    }

    public void setCritical(String message)
    {
        setAlert(DataSourceAlertStatus.CRITICAL, message);
    }

    /**
     * @param isAvailable the isDateAvailable to set
     */
    @JsonIgnore
    public void setIsAvailable(boolean isAvailable)
    {
        this.available = isAvailable;

        if (isAvailable)
        {
            setState(ResourceState.ONLINE);
            setAlert(DataSourceAlertStatus.OK, "");
        }
        else
        {
            setState(ResourceState.OFFLINE);
        }

        setLastShunReason("");
        setLastError("");
    }

    /**
     * Prevent the driver from pDaterocessing new connection requests. If the
     * driver is disabled, it will either cause new connection requests to wait
     * or will throw a SQLException.
     * 
     * @throws InterruptedException
     */
    public void disable() throws InterruptedException
    {
        // If waitFlag is true, we need to wait until all
        // active connections are completed.

        synchronized (enabled)
        {
            if (enabled.get() == 0)
            {
                return;
            }
            enabled.set(0);
        }
    }

    /**
     * Update a given datasource with values from a different datasource
     * 
     * @param ds
     */
    public void update(DataSource ds)
    {
        synchronized (this)
        {
            sequence.next();
            this.setName(ds.getName());
            this.setVendor(ds.getVendor());
            this.setDataServiceName(ds.getDataServiceName());
            this.setDriver(ds.getDriver());
            this.setUrl(ds.getUrl());
            this.setRole(ds.getRole());
            this.setPrecedence(ds.getPrecedence());
            this.setIsAvailable(ds.getIsAvailable());
            this.setState(ds.getState());
            this.setLastError(ds.getLastError());
            this.setLastShunReason(ds.getLastShunReason());
            this.setAppliedLatency(ds.getAppliedLatency());
            this.setUpdateTimestamp(ds.getUpdateTimestamp());
            this.setLastError(ds.getLastError());
            this.setHighWater(ds.getHighWater());
            this.setVipAddress(ds.getVipAddress());
            this.setVipInterface(ds.getVipInterface());
            this.setVipIsBound(ds.getVipIsBound());
            this.setLastUpdateToNow();
            this.notifyAll();
        }
    }

    public TungstenProperties toProperties()
    {
        TungstenProperties props = new TungstenProperties();

        props.setString(NAME, getName());
        props.setString(VENDOR, getVendor());
        props.setString(CLUSTERNAME, getDataServiceName());
        props.setString(HOST, getHost());
        props.setString(DRIVER, getDriver());
        props.setString(URL, getUrl());
        props.setString(ROLE, getRole().toString());
        props.setString(ALERT_STATUS, alertStatus.toString());
        props.setString(ALERT_MESSAGE, alertMessage);
        props.setLong(ALERT_TIME, alertTime);
        props.setInt(PRECEDENCE, getPrecedence());
        props.setBoolean(ISAVAILABLE, getIsAvailable());
        props.setString(STATE, getState().toString());
        props.setString(LASTERROR, getLastError());
        props.setString(LASTSHUNREASON, getLastShunReason());
        props.setDouble(APPLIED_LATENCY, appliedLatency);
        props.setLong(ACTIVE_CONNECTION_COUNT, activeConnectionsCount.get());
        props.setLong(CONNECTIONS_CREATED_COUNT, connectionsCreatedCount.get());
        props.setLong("statementsCreatedCount", statementsCreatedCount.get());
        props.setLong("preparedStatementsCreatedCount",
                preparedStatementsCreatedCount.get());
        props.setLong("callableStatementsCreatedCount",
                callableStatementsCreatedCount.get());
        props.setString("highWater", highWater.toString());
        props.setString("sequence", sequence.toString());

        props.setString(VIPADDRESS, getVipAddress());
        props.setString(VIPINTERFACE, getVipInterface());
        props.setBoolean(VIPISBOUND, getVipIsBound());
        props.setBoolean(ISCOMPOSITE, isComposite());

        return props;
    }

    /**
     * TODO: toMap definition.
     * 
     * @return properties representing this datasource
     */
    public Map<String, String> toMap()
    {
        return toProperties().hashMap();
    }

    /**
     * Creates a new <code>DataSource</code> object
     * 
     * @param dsProperties
     */
    public DataSource(Map<String, String> dsProperties)
    {
        set(dsProperties);
    }

    public void set(Map<String, String> dsProperties)
    {
        TungstenProperties props = new TungstenProperties(dsProperties);
        props.applyProperties(this, true);
    }

    /**
     * Returns the sequence value.
     * 
     * @return Returns the sequence.
     */
    @JsonIgnore
    public Sequence getSequence()
    {
        return sequence;
    }

    public void incrementActiveConnections()
    {

        long count = activeConnectionsCount.incrementAndGet();
        logger.debug("Incremented connections for datasource: name="
                + this.getName() + " count=" + count);

    }

    public void decrementActiveConnections()
    {

        long count = activeConnectionsCount.decrementAndGet();
        logger.debug("Decremented connections for datasource: name="
                + this.getName() + " count=" + count);

    }

    /**
     * Returns the number of connections created on this datasource.
     */
    @JsonProperty()
    public long getConnectionsCreated()
    {
        return connectionsCreatedCount.get();
    }

    public void incrementConnectionsCreated()
    {
        connectionsCreatedCount.incrementAndGet();
    }

    /**
     * Returns the number of JDBC Statement instances created.
     */
    public long getStatementsCreated()
    {
        return statementsCreatedCount.get();
    }

    public void incrementStatementsCreated()
    {
        this.statementsCreatedCount.incrementAndGet();
    }

    /**
     * Returns the number of JDBC PreparedStatement instances created.
     */
    public long getPreparedStatementsCreated()
    {
        return preparedStatementsCreatedCount.get();
    }

    public void incrementPreparedStatementsCreated()
    {
        this.preparedStatementsCreatedCount.incrementAndGet();
    }

    /**
     * Returns the number of JDBC CallableStatement instances created.
     */
    public long getCallableStatementsCreated()
    {
        return callableStatementsCreatedCount.get();
    }

    public void incrementCallableStatementsCreated()
    {
        this.callableStatementsCreatedCount.incrementAndGet();
    }

    /**
     * Returns the dataServiceName value.
     * 
     * @return Returns the dataServiceName.
     */
    public String getDataServiceName()
    {
        return dataServiceName;
    }

    /**
     * Sets the dataServiceName value.
     * 
     * @param dataServiceName The dataServiceName to set.
     */
    public void setDataServiceName(String dataServiceName)
    {
        this.dataServiceName = dataServiceName;
    }

    /**
     * Format a datasource for display
     */
    public double getAppliedLatency()
    {
        return appliedLatency;
    }

    /**
     * Sets the last seen latency of this data source
     * 
     * @param appliedLatency update appliedLatency observed
     */
    public void setAppliedLatency(double appliedLatency)
    {
        this.appliedLatency = appliedLatency;
    }

    /**
     * Format a datasource for display
     */
    @Override
    public String toString()
    {
        return String.format("%s@%s(%s:%s) STATUS(%s)", getName(),
                getDataServiceName(), getRole(), getState(), getAlertStatus());
    }

    /**
     * Gives the last time this data source received an update
     * 
     * @return the last update time
     */
    public Date getLastUpdate()
    {
        return lastUpdate;
    }

    /**
     * Sets the lastUpdate field to the current instant in time
     */
    private void setLastUpdateToNow()
    {
        this.lastUpdate = new Date();
    }

    /**
     * Sets the sequence value.
     * 
     * @param sequence The sequence to set.
     */
    public void setSequence(Sequence sequence)
    {
        this.sequence = sequence;
    }

    /**
     * Returns the host value.
     * 
     * @return Returns the host.
     */
    public String getHost()
    {
        return host;
    }

    /**
     * Sets the host value.
     * 
     * @param host The host to set.
     */
    public void setHost(String host)
    {
        this.host = host;
    }

    @JsonIgnore
    public HighWaterResource getHighWater()
    {
        return highWater;
    }

    public void setHighWater(HighWaterResource highWater)
    {
        this.highWater = highWater;
    }

    public void setHighWater(long epoch, String eventId)
    {
        if (this.highWater != null)
        {
            this.highWater.update(epoch, eventId);
        }
        else
        {
            this.setHighWater(new HighWaterResource(epoch, eventId));
        }
    }

    public AtomicInteger getEnabled()
    {
        return enabled;
    }

    public void setEnabled(AtomicInteger enabled)
    {
        this.enabled = enabled;
    }

    public boolean isMaster()
    {
        return role == DataSourceRole.master;
    }

    public boolean isSlave()
    {
        return role == DataSourceRole.slave;
    }

    public boolean isRelay()
    {
        return role == DataSourceRole.relay;
    }

    public ResourceState getState()
    {
        return state;
    }

    public void setState(ResourceState state)
    {
        this.state = state;
    }

    public void setFailed(String error)
    {
        setIsAvailable(false);

        this.state = ResourceState.FAILED;
        setAlert(DataSourceAlertStatus.CRITICAL, "state was set to FAILED");

        if (error != null)
        {
            this.lastError = error;
        }
    }

    public void setShunned(String reason)
    {
        setIsAvailable(false);

        this.state = ResourceState.SHUNNED;
        setAlert(DataSourceAlertStatus.SHUNNED, "");

        if (reason != null)
        {
            this.lastShunReason = reason;
            this.lastError = "";
        }
    }

    public String getLastError()
    {
        return lastError;
    }

    public void setLastError(String lastError)
    {
        this.lastError = lastError;
    }

    public String getLastShunReason()
    {
        return lastShunReason;
    }

    public void setLastShunReason(String lastShunReason)
    {
        this.lastShunReason = lastShunReason;
    }

    public static DataSource copy(DataSource ds)
    {
        return new DataSource(ds.toProperties());
    }

    public String getVipInterface()
    {
        return vipInterface;
    }

    public void setVipInterface(String vipInterface)
    {
        this.vipInterface = vipInterface;
    }

    public String getVipAddress()
    {
        return vipAddress;
    }

    public void setVipAddress(String vipAddress)
    {
        this.vipAddress = vipAddress;
    }

    public boolean getVipIsBound()
    {
        return vipIsBound;
    }

    public void setVipIsBound(boolean vipIsBound)
    {
        this.vipIsBound = vipIsBound;
    }

    public Date getUpdateTimestamp()
    {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp)
    {
        this.updateTimestamp = updateTimestamp;
    }

    public DataSourceAlertStatus getAlertStatus()
    {
        return alertStatus;
    }

    public void setAlertStatus(DataSourceAlertStatus alertStatus)
    {
        this.alertStatus = alertStatus;
    }

    public String getAlertMessage()
    {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage)
    {
        this.alertMessage = alertMessage;
    }

    public void setAlert(DataSourceAlertStatus status, String message)
    {
        alertStatus = status;
        alertMessage = message;
        alertTime = System.currentTimeMillis();
    }

    public long getAlertTime()
    {
        return alertTime;
    }

    public void setAlertTime(long alertTime)
    {
        this.alertTime = alertTime;
    }

    public boolean isComposite()
    {
        return isComposite;
    }

    public void setComposite(boolean isComposite)
    {
        this.isComposite = isComposite;
    }

    public void setIsComposite(boolean isComposite)
    {
        this.isComposite = isComposite;
    }

    /**
     * Returns the standby value.
     * 
     * @return Returns the standby.
     */
    public boolean isStandby()
    {
        return getRole().equals(DataSourceRole.standby.toString());
    }

    /**
     * Sets the standby value.
     */
    public void setStandby(boolean isStandby)
    {
        this.isStandby = isStandby;
    }

    /**
     * Set the activeConnectionCount for this data source from the count passed
     * via a long
     * 
     * @param activeConnectionCount
     */
    public void setActiveConnectionCount(long activeConnectionCount)
    {
        this.activeConnectionsCount.set(activeConnectionCount);
    }

    /**
     * Set the connectionsCreatedCount for this data source from the count
     * passed via a long
     * 
     * @param connectionsCreatedCount
     */
    public void setConnectionsCreatedCount(long connectionsCreatedCount)
    {
        this.connectionsCreatedCount.set(connectionsCreatedCount);
    }
}
