from bs4 import BeautifulSoup
import requests
import firebase_admin
from firebase_admin import credentials, messaging
from firebase_admin import db
import threading
import datetime

#varibales
state_names=[]


#base url:
base_url="https://market.todaypricerates.com/"

#firebase
info = credentials.Certificate("info.json")
infoo=firebase_admin.initialize_app(info, {
    'databaseURL': "https://price-of-food-default-rtdb.firebaseio.com/"
},name='infoo')



def state_name():
    st = (("Andaman and Nicobar","AN"),("Andhra Pradesh","AP"),("Arunachal Pradesh",'AR'),("Assam","AS"),("Bihar","BR"),("Chhattisgarh","CG"),("Chandigarh","CH"),("Dadra and Nagar Haveli","DN"),("Daman and Diu","DD"),
       ("Delhi","DL"),("Goa","GA"),("Gujarat","GJ"),("Haryana","HR"),("Himachal Pradesh","HP"),("Jammu and Kashmir","JK"),("Jharkhand","JH"),
       ("Karnataka","KA"),("Kerala","KL"),("Lakshadweep","LD"),("Madhya Pradesh","MP"),("Maharashtra","MH"),("Manipur","MN"),
       ("Meghalaya","ML"),("Mizoram","MZ"),("Nagaland","NL"),("Odisha","OD"),("Punjab","PB"),("Puducherry","PY"),("Rajasthan","RJ"),
       ("Sikkim","SK"),("Tamil Nadu","TN"),("Telangana","TS"),("Tripura","TR"),
          ("Uttar Pradesh","UP"),("Uttarakhand","UK"),("West Bengal","WB"))

    code=dict(st)
    global state_names
    ref = db.reference('Data',infoo)
    url=base_url+"vegetables-daily-price"
    page=requests.get(url)
    data=BeautifulSoup(page.text,'html.parser')
    shop_table=data.find("table",attrs={'class':'shop_table'})
    tr=shop_table.findAll('a')
    for i in tr:
        ref.child("StateName").child(i.text.replace(" ","-")).child('code').set(code[i.text])
        ref.child("StateName").child(i.text.replace(" ","-")).child('name').set(i.text.replace(" ","-"))
        state_names.append(i.text.replace(" ","-"))




def veg():
    ref = db.reference('Data',infoo)
    for i1 in state_names:
        url=base_url+i1+"-vegetables-price"
        
        page=requests.get(url)
        data=BeautifulSoup(page.text,'html.parser')
        d = data.find('div', attrs={'class': 'Table'})
            
        d = d.findAll('div', attrs={'class': 'Row'})
        li=[]
        for i in range(0,len(d)):
            li.append(d[i].text)
            x = li[i].replace("\n","*").split("* ")
            x[0]=(x[0].replace("*"," "))
            x[0]=(x[0].replace("Kg / Pcs"," "))
            ref.child("Vegtables").child(i1).child(x[0]).child("VegetableName").set(x[0])
            ref.child("Vegtables").child(i1).child(x[0]).child("MarketPrice").set(x[1].replace("₹",""))
            ref.child("Vegtables").child(i1).child(x[0]).child("RetailPrice").set(x[2].replace("₹",""))
            ref.child("Vegtables").child(i1).child(x[0]).child("ShoppingMall").set(x[3].replace("*","").replace("₹",""))
    print("Veg  completed ..",datetime.datetime.now().time())
    
    
def fruits():
    ref1 = db.reference('Data',infoo)
    for i1 in state_names:
        
        url=base_url+i1+"-fruits-price"
        page=requests.get(url)
        data=BeautifulSoup(page.text,'html.parser')
        d = data.find('div', attrs={'class': 'Table'})
                
        d = d.findAll('div', attrs={'class': 'Row'})
        li=[]
        for i in range(0,len(d)):
            li.append(d[i].text)

            x = li[i].replace("\n","*").split("* ")

            x[0]=(x[0].replace("*"," "))
            x[0]=(x[0].replace("Kg / Pcs"," "))
            ref1.child("Fruits").child(i1).child(x[0]).child("FruitsName").set(x[0])
            ref1.child("Fruits").child(i1).child(x[0]).child("MarketPrice").set(x[1].replace("₹",""))
            ref1.child("Fruits").child(i1).child(x[0]).child("RetailPrice").set(x[2].replace("₹",""))
            ref1.child("Fruits").child(i1).child(x[0]).child("ShoppingMall").set(x[3].replace("*","").replace("₹",""))
    print("fruits  completed ..",datetime.datetime.now().time())

def egg():
    ref2 = db.reference('Data',infoo)
    for i1 in state_names:
        
        if(i1=="Andaman-and-Nicobar"):
            url=base_url+i1+"-Islands-egg-rate"
        else:
            url=base_url+i1+"-egg-rate"
            
       
        page=requests.get(url)
        data=BeautifulSoup(page.text,'html.parser')
        data=data.find('div',attrs={'id':'order_review'})
        d = data.find('table', attrs={'class': 'shop_table'})
        d=d.find('tfoot')
        d = d.findAll('tr',)
        li=[]
        

        for i in d:
            x =i.text.replace(" ","*").split("*")            
            ref2.child("Egg").child(i1).child('egg').child("Date").set(x[0])
            ref2.child("Egg").child(i1).child('egg').child("price").set(x[2].replace('₹',''))
            
            break
    print("Egg  completed ..",datetime.datetime.now().time())
def non_veg():
    refnon = db.reference('Data',infoo)
    for i1 in state_names:
        if(i1=="Andaman-and-Nicobar"):
            url=base_url+i1+"-Islands-chicken-mutton-beef-fish-rate"
        else:
            url=base_url+i1+"-chicken-mutton-beef-fish-rate"
        page=requests.get(url)
        data=BeautifulSoup(page.text,'html.parser')
        shop_table=data.findAll("table",attrs={'class':'shop_table'})
        def ch():
            shop_table[0]=(shop_table[0].text.replace("\n","*"))
            a=(shop_table[0].split('*'))
            while("" in a):
                a.remove("")
            #print(a[1].split('₹')[0],a[1].split('₹')[1])
            #print(a[2].split('₹')[0],a[2].split('₹')[1])
            #print(a[3].split('₹')[0],a[3].split('₹')[1])
                  
            refnon.child("nonveg").child(i1).child("chicken").child("price").delete()
            
            refnon.child("nonveg").child(i1).child("chicken").child("skin").child('price').set(a[1].split('₹')[1])
            refnon.child("nonveg").child(i1).child("chicken").child("skin").child('name').set("skin")

            refnon.child("nonveg").child(i1).child("chicken").child("skinless").child('price').set(a[2].split('₹')[1])
            refnon.child("nonveg").child(i1).child("chicken").child("skinless").child('name').set("skinless")

            refnon.child("nonveg").child(i1).child("chicken").child("boneless").child('price').set(a[3].split('₹')[1])
            refnon.child("nonveg").child(i1).child("chicken").child("boneless").child('name').set("boneless")
            
            
            
            
            
           
        def mt():
            shop_table[1]=(shop_table[1].text.replace("\n","*"))
            a=(shop_table[1].split('*'))
            while("" in a):
                a.remove("")
            #print(a[1].split('₹')[0],a[1].split('₹')[1])
            refnon.child("nonveg").child(i1).child("mutton").child('price').child('price').set(a[1].split('₹')[1])
            refnon.child("nonveg").child(i1).child("mutton").child('price').child('name').set("mutton")
            
        def bf():
            shop_table[2]=(shop_table[2].text.replace("\n","*"))
            a=(shop_table[2].split('*'))
            while("" in a):
                a.remove("")
            #print(a[1].split('₹')[0],a[1].split('₹')[1])
                refnon.child("nonveg").child(i1).child("beff").child('price').child('name').set('beff')
            refnon.child("nonveg").child(i1).child("beff").child('price').child('price').set(a[1].split('₹')[1])
        def fish():
            shop_table[3]=(shop_table[3].text.replace("\n","*"))
            a=(shop_table[3].split('*'))
            while("" in a):
                a.remove("")
            for i in range(1,len(a)):
                #print(a[i].split('₹')[0].replace(')','').replace('(','').replace(' ',''),a[i].split('₹')[1])
                refnon.child("nonveg").child(i1).child('fish').child(a[i].split('₹')[0].replace(')','').replace('(','').replace(' ','')).child('name').set(a[i].split('₹')[0].replace(')','').replace('(','').replace('Kilogram',''))
                refnon.child("nonveg").child(i1).child('fish').child(a[i].split('₹')[0].replace(')','').replace('(','').replace(' ','')).child('price').set(a[i].split('₹')[1])
        ch()
        mt()
        bf()
        fish()

    print("Non-Veg  completed ..",datetime.datetime.now().time())
def petrol():
    refpt = db.reference('Data',infoo)
    url="https://economictimes.indiatimes.com/wealth/fuel-price/petrol"
    data=requests.get(url)
    soup = BeautifulSoup(data.text, 'html.parser')
    d = soup.find('div', attrs={'class': 'content'})
    d=d.find('tbody')
    d=d.findAll('tr')
    kk=[]
    for i in d:
        data=i.findAll('td')
        for j in data:
            kk.append(j.text)

    for i in range(0,len(kk),3):
        #print(kk[i]+"->"+kk[i+1] +"->"+kk[i+2])
        refpt.child('petrol').child(kk[i]).child('name').set(kk[i])
        refpt.child('petrol').child(kk[i]).child('price').set(kk[i+1].replace("₹/L",""))
        refpt.child('petrol').child(kk[i]).child('change').set(kk[i+2])



    print("Petrol  completed ..",datetime.datetime.now().time())

def astro():
    refas = db.reference('Data',infoo)
    title=['Aries','Taurus','Gemini','Cancer','Leo','Virgo','Libra','Scorpio','Sagittarius','Capricorn','Aquarius','Pisces']
    for j in title:
        
        main_text=""
        url="https://www.astrosage.com/horoscope/daily-"+j.lower()+"-horoscope.asp"
        page=requests.get(url)
        data=BeautifulSoup(page.text,'html.parser')
        da=data.find('div',attrs={'class':'ui-large-content-box'})
        da=da.findAll('div')
        for i in range(0,6):
            if(i==2):
                pass
            elif(i>=3):
                refas.child("astro").child(j).child(da[i].text.split(":-")[0].replace(' ','').lower()).set(da[i].text.split(":-")[1])
                #print(da[i].text.split(":-"),"\n")
            elif i==0:
                #print(da[i].text,"\n")
                refas.child("astro").child(j).child("date").set(da[i].text)
            elif 1==1:
                refas.child("astro").child(j).child("message").set(da[i].text)
    print("Astro  completed ..",datetime.datetime.now().time())
    
print("Starting time : ",datetime.datetime.now().time())
state_name()

petrol = threading.Thread(target=petrol,)
non_veg = threading.Thread(target=non_veg,)
fruits = threading.Thread(target=fruits,)
egg = threading.Thread(target=egg,)
veg = threading.Thread(target=veg,)
astro = threading.Thread(target=astro,)

petrol.start()
non_veg.start()

egg.start()
veg.start()
astro.start()
fruits.start()



