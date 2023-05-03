LogGenerator generates a log file, activity.log, to mimic user log in and log out.
Grok Exporter uses regular expression patterns to parse the activity log and translates user activities into Prometheus metrics.
Prometheus scrapes metrics from Grok Exporter and displays & visualizes user activity metrics.  

Run LogGenerator:
java -cp C:\Projects\LogGenerator\bin org.dragon.yunpeng.LogGenerator

Run Grok Exporter:
grok_exporter -config C:\Projects\LogGenerator\Config\config_activity_log.yml

Access Grok Exporter Metrics in browser:
http://localhost:9144/user_activity_metrics

Run Promethus:
prometheus --config.file=C:\Projects\LogGenerator\Config\prometheus.yml

Access Promethus:
http://localhost:9090

Metric: user_activity


------------------------------------------------------------------------------
References:

Grok_Exporter:
https://github.com/fstab/grok_exporter

Promethus:
https://prometheus.io