# Task 1: Design a system

## Task Overview

### What you'll learn

- How to create a high-level design for the system which satisfies all of the requirements

### What you'll do

- Come up with a design for the new system
- Draft a visualization of the system

## Task Background

Today you’ll be stepping into the role of a principal software engineer leading a small team of developers here at PepsiCo. Throughout this program, you’ll develop a new predictive supply chain management system called Augur. Augur is intended to provide greater visibility into the decisions made by PepsiCo’s predictive shipping models. Augur will consist of a frontend that displays various metrics on PepsiCo’s distributors and a backend that integrates with the existing predictive shipping data pipeline. The system will examine the flow of goods to PepsiCo’s distributors, providing a comprehensible sanity check for the process. Furthermore, Augur will expose at-a-glance metrics that can be used to assess supply chain health.

You’ve been handed a list of requirements for Augur and asked to see it through to production. It won’t be a small undertaking, but it’s hardly overwhelming for someone of your expertise. The first step will be to create a high-level design for the system which satisfies all of the aforementioned requirements. Remember that your goal here is two-fold. You must come up with a practical design for the system, and you must articulate your design well.

## Task Detail

Before your team can begin working on Augur, they must understand what they’re trying to build. Your task is to come up with a design for the new predictive supply chain management system which fulfills the following requirements:

- Augur must surface a browser-based dashboard for users.
- Augur’s dashboard must display the following metrics for each PepsiCo distributor:
  - Name.
  - The year-to-date average quantity of goods shipped.
  - Quantity of goods shipped last month.
  - Forecasted quantity of goods to ship this month.
- Augur must integrate with the existing predictive shipping pipeline. Assume that your backend services have access (via messaging queues) to the monthly shipment quantities for all of PepsiCo’s distributors and next month’s forecasts for each distributor as they become available.
- The system must be deployed to Azure.
- The system should emphasize extendibility. While the above are the only requirements for the MVP, Augur is expected to grow significantly, so adding additional functionality should be painless.

When you have an idea of how you’d like to design your system, draft a visualization. Your visualization should focus on service-level interactions within the Augur system. Note that the above requirements are intentionally sparse to encourage your own creativity. The goal of this task is to demonstrate that you can, with very little assistance, create a fully realized system, and articulate it to your co-workers. When you finish your design, save it as an image and submit it below.
