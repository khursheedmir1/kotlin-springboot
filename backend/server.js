const express = require('express');
const LaunchDarkly = require('launchdarkly-node-server-sdk');
require('dotenv').config();

const app = express();
app.use(express.json());
const PORT = 4000;

const ldClient = LaunchDarkly.init(process.env.LD_SDK_KEY);

async function init() {
  await ldClient.waitForInitialization();
  console.log('✅ LaunchDarkly SDK initialized');

  app.get('/feature', async (req, res) => {
    const userKey = req.query.user || 'user-123';
    const user = { key: userKey };
    const showNewHomepage = await ldClient.variation('new-homepage', user, false);
    res.json({ user: userKey, newHomepage: showNewHomepage });
  });

  app.post('/convert', (req, res) => {
    const { user } = req.body;
    ldClient.track('homepage-conversion', { key: user });
    res.json({ status: `Conversion tracked for ${user}` });
  });

  app.listen(PORT, () => console.log(`🚀 Backend running at http://localhost:${PORT}`));
}

init();