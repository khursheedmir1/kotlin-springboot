# LaunchDarkly Full Demo (Node + React)

This project shows how to use LaunchDarkly for:
1. Basic feature toggles
2. Percentage rollouts
3. Experiments with conversion tracking

## Setup

### Backend
```bash
cd backend
npm install
```
Create `.env`:
```
LD_SDK_KEY=YOUR_SERVER_SDK_KEY
```
Run:
```bash
npm start
```

### Frontend
```bash
cd frontend
npm install
```
Edit `src/App.js` → replace `YOUR_CLIENT_SIDE_ID`.
Run:
```bash
npm start
```

## Usage
- Create flag `new-homepage` (boolean).
- Add percentage rollout (50/50).
- Enable experiment with goal `homepage-conversion`.

Backend:  
- `GET /feature?user=alice` → flag value  
- `POST /convert { "user": "alice" }` → conversion

Frontend:  
- Switch between Alice and Bob → rollout difference  
- Click "Simulate Conversion" → sends conversion event  

Check Experiments in LD dashboard to compare conversion rates.
