select date_trunc('second',processdate :: timestamp) as processdate, * from serverinfo where servername = /* serverName */'svn' order by serverinfo.processdate asc limit 15

