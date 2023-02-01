local set = redis.call('zrangebyscore', 'delay_zset', 0, ARGV[1])
for i,v in ipairs(set)
do
    redis.call('lpush', 'delay_list',v)
end
return set