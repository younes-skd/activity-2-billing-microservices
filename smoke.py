#!/usr/bin/env python3
import json, urllib.request, sys
def req(port,path,body=None,method=None):
    request=urllib.request.Request(f'http://localhost:{port}{path}',data=json.dumps(body).encode() if body is not None else None,method=method,headers={'Content-Type':'application/json','Origin':'http://localhost:4200'})
    with urllib.request.urlopen(request,timeout=180) as response:
        if port==8880:
            assert response.headers.get_all('Access-Control-Allow-Origin')==['http://localhost:4200'], 'Gateway must return one CORS origin header'
        data=response.read();return json.loads(data) if data else None

assert req(8880,'/api/customers');assert req(8880,'/api/products')
b=req(8880,'/api/bills',{'customerId':1,'lines':[{'productId':1,'quantity':2}]});assert b['total']==2600
assert req(8880,'/api/bills/'+str(b['id']))['customer']['id']==1
assert req(8880,'/customer-service/customers')
assert req(8888,'/customer-service/default')['propertySources']
print('PASS: real Gateway, Eureka, Feign billing and Config Server')
