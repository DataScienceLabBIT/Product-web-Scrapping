from firebase_admin import storage
from firebase_admin import credentials, messaging
import firebase_admin
from firebase_admin import db
import os

info = credentials.Certificate("E:\CODEING\python\BS4\Price getting of all foods\info.json")
infoo=firebase_admin.initialize_app(info, {
    'databaseURL': "https://price-of-food-default-rtdb.firebaseio.com/"
},name='infoo')

bucket_name = '{0}.appspot.com'.format("price-of-food")
bucket = storage.bucket(bucket_name,infoo)

st = (("Andaman and Nicobar","AN"),("Andhra Pradesh","AP"),("Arunachal Pradesh",'AR'),("Assam","AS"),("Bihar","BR"),("Chhattisgarh","CG"),("Chandigarh","CH"),("Dadra and Nagar Haveli","DN"),("Daman and Diu","DD"),
       ("Delhi","DL"),("Goa","GA"),("Gujarat","GJ"),("Haryana","HR"),("Himachal Pradesh","HP"),("Jammu and Kashmir","JK"),("Jharkhand","JH"),
       ("Karnataka","KA"),("Kerala","KL"),("Lakshadweep","LD"),("Madhya Pradesh","MP"),("Maharashtra","MH"),("Manipur","MN"),
       ("Meghalaya","ML"),("Mizoram","MZ"),("Nagaland","NL"),("Odisha","OD"),("Punjab","PB"),("Puducherry","PY"),("Rajasthan","RJ"),
       ("Sikkim","SK"),("Tamil Nadu","TN"),("Telangana","TS"),("Tripura","TR"),("Uttar Pradesh","UP"),
      ("Uttarakhand","UK"),("West Bengal","WB"))


ref = db.reference('Data',infoo)
ref.child('Vegtables').child('Ladakh').delete()
ref.child('Fruits').child('Ladakh').delete()
'''
for i in range(0,len(st)):
    print(st[i][0].replace(" ","-"))
    ref.child('Vegtables').child('Ladakh').delete()
    
    directory = "C:/Users/MrProgrammer/Downloads/pri/"
    for foldername in os.listdir(directory):
        if str(foldername[0]) != '.':       
            for image in os.listdir(directory + foldername):
                if image!="Thumbs.db":
                    
                    blob = bucket.blob(f'{foldername}/{image}')
                    imagePath = directory + foldername + '/' + image
                    blob.upload_from_filename(imagePath)
                    blob.make_public()
                    name=image.replace('.png','')
                    print(name)
                    print(blob.public_url)
                    ref.child(foldername).child(st[i][0].replace(" ","-")).child(" "+name+"  ").child('img').set(blob.public_url)  '''              
                    
          
