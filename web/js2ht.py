import os
import shutil
import string
name=os.listdir(".")
for i in name:
    n=string.rfind(i,".jsp")
    if n!=-1:
        shutil.copy(i,i[0:n]+".htm")
