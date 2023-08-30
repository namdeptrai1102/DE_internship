from flask import render_template
from app import app
@app.route('/') 
      def home(): 
      return "hello world!"

@app.route('/ping')
def ping():
   return "Pong!"

@app.route('/template')
def template():
    return render_template('home.html')
